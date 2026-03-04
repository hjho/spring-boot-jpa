package com.document.study.jpa.business.repository;

import java.util.List;
import java.util.stream.Collectors;

import com.document.study.jpa.business.controller.data.AssignPlayerPDto;
import com.document.study.jpa.business.controller.data.AssignPlayerRDto;
import com.document.study.jpa.business.controller.data.AssignPlayerVO;
import com.document.study.jpa.business.controller.data.QAssignPlayerRDto;
import com.document.study.jpa.business.controller.data.QPlayerCardRDto;
import com.document.study.jpa.business.controller.data.QPlayerPositionRDto;
import com.document.study.jpa.business.controller.data.QPlayerRDto;
import com.document.study.jpa.business.controller.data.QTeamInfoRDto;
import com.document.study.jpa.business.controller.data.QTeamRDto;
import com.document.study.jpa.entity.AssignPlayer;
import com.document.study.jpa.entity.Player;
import com.document.study.jpa.entity.PlayerCard;
import com.document.study.jpa.entity.PlayerPosition;
import com.document.study.jpa.entity.QAssignPlayer;
import com.document.study.jpa.entity.QPlayer;
import com.document.study.jpa.entity.QPlayerCard;
import com.document.study.jpa.entity.QPlayerPosition;
import com.document.study.jpa.entity.QTeam;
import com.document.study.jpa.entity.QTeamInfo;
import com.document.study.jpa.entity.Team;
import com.document.study.jpa.entity.TeamInfo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class AssignPlayerRepositoryImpl implements AssignPlayerRepositoryCustom {
	
	
	private final JPAQueryFactory jpaQueryFactory;

	/* 순환 참조로 인해 아래와 같이 응답이 내려감.
	 *   Team 과 Player 객체는 없어서 다른 객체로 변환해서 응답을 내려줘야 함.
		{
		  "backNo": 17,
		  "nickName": "관악산 호랑이"
		} 
	 */
	@Override
	public AssignPlayer findQueryDslOne(String team, String player) {
		QAssignPlayer assignPlayer = QAssignPlayer.assignPlayer;
		
		JPAQuery<AssignPlayer> query = jpaQueryFactory.selectFrom(assignPlayer);
		
		query
			.where(assignPlayer.team.code.eq(team)
				.and(assignPlayer.player.code.eq(player)));
		
		return query.fetchOne();
	}
	
	/**
	 * 기본적인 stream 을 이용하여 객체 변환 후 응답. 
	 *   AssignPlayer(Team, TeamInfo, Player, PlayerCard, PlayerPosition)
	 *   위 객체들을 다 포함하여 응답.
	 */
	@Override
	public List<AssignPlayerVO> findQueryDslAll() {
		
		List<AssignPlayer> players = jpaQueryFactory.selectFrom(QAssignPlayer.assignPlayer).fetch();
		
		List<AssignPlayerVO> playerVOs = 
				players.stream().map((player) -> new AssignPlayerVO(player)).collect(Collectors.toList());
		
		/** stream 과 생성자를 이용해서 응답 값을 설정.
			public AssignPlayerVO(AssignPlayer assignPlayer) {
		    	this.team = assignPlayer.getTeam();
		    	this.player = assignPlayer.getPlayer();
		    	this.backNo = assignPlayer.getBackNo();
		        this.nickName = assignPlayer.getNickName();
		        // LAZY.
		        this.player.setPosition(new ArrayList<>(assignPlayer.getPlayer().getPosition()));
		    }
		 */
		return playerVOs;
	}

	
	/**
	 * Projections 을 이용하여 객체 변환 후 응답. 
	 *   AssignPlayer(Team, TeamInfo, Player, PlayerCard, PlayerPosition)
	 *   위 객체들을 다 포함하여 응답.
	 *   
	 *   Projections.fields() 필드에 직접 연결하는 맵핑
	 *   Projections.bean() setter 를 이용한 맵핑
	 */
	@Override
	public List<AssignPlayerVO> findQueryDslJoin() {
		QAssignPlayer qAssignPlayer = QAssignPlayer.assignPlayer;
		QTeam qTeam = QTeam.team;
		QTeamInfo qTeamInfo = QTeamInfo.teamInfo;
		QPlayer qPlayer = QPlayer.player;
		QPlayerCard qPlayerCard = QPlayerCard.playerCard;
		QPlayerPosition qPlayerPosition = QPlayerPosition.playerPosition;
		
		QBean<Team> qBeanTeam = Projections.fields(
											Team.class,
											qTeam.code,
											qTeam.name,
											Projections.fields(
													TeamInfo.class,
													// qTeamInfo.team,
													qTeamInfo.address,
													qTeamInfo.mobile,
													qTeamInfo.ownerName
											).as("info"));
		
		QBean<Player> qBeanPlayer = Projections.fields(
											Player.class,
											qPlayer.code,
											qPlayer.name,
											qPlayer.age,
											qPlayer.created,
											qPlayer.updated,
											Projections.fields(
													PlayerCard.class,
													// qPlayerCard.player,
													qPlayerCard.brandNm,
													qPlayerCard.cardNo
											).as("card"),
											GroupBy.list(
													Projections.fields(
															PlayerPosition.class,
															// qPlayerCard.player,
															qPlayerPosition.code,
															qPlayerPosition.career
													)
											).as("position"));
		
		QBean<AssignPlayerVO> qBeanAssign = Projections.bean(
												AssignPlayerVO.class, 
													ExpressionUtils.as(qBeanTeam, "team"),
													ExpressionUtils.as(qBeanPlayer, "player"),
													qAssignPlayer.backNo,
													qAssignPlayer.nickName
											);
		
		JPAQuery<AssignPlayer> query = jpaQueryFactory.selectFrom(qAssignPlayer);
															
		query
			.innerJoin(qAssignPlayer.team, qTeam)
				.innerJoin(qAssignPlayer.team.info, qTeamInfo)
			.innerJoin(qAssignPlayer.player, qPlayer)
				.leftJoin(qAssignPlayer.player.card, qPlayerCard)
				.leftJoin(qAssignPlayer.player.position, qPlayerPosition)
			.where(qAssignPlayer.team.code.eq("T01"))
			.orderBy(qAssignPlayer.player.age.asc());
		
		return query.transform(
						GroupBy.groupBy(qAssignPlayer.team.code, qAssignPlayer.player.code).list(
								qBeanAssign
						)
				);
	}

	
	/**
	 * Q클래스 을 이용하여 객체 변환 후 응답. 
	 *   AssignPlayer(Team, TeamInfo, Player, PlayerCard, PlayerPosition)
	 *   위 객체들을 다 포함하여 응답.
	 *   
	 * @QueryProjection new QAssignPlayerRDto()
	 *   AssignPlayerRDto 생성자에 붙은 어노테이션으로 QueryDsl 이 처리해준다.
	 *   근데 Projections.fields 방식이랑 별로 차이 없는 것 같다.
	 * 
	 * 이점 이라는데,,,
	 *   1. 컴파일 시점의 타입 체크 (가장 큰 장점)
     *   2. 코드 가독성과 간결함
     *   3. 복잡한 중첩 구조 해결
     *   그래도 단순히 Entity 와 Dto를 분리하는 측면에서는 좋아보임.
     *     - Projections 을 이용하면 @NoArgsConstructor 를 public 으로 변경해야 한다.
	 */
	@Override
	public List<AssignPlayerRDto> findQueryDslDynamic(AssignPlayerPDto input) {
		QAssignPlayer qAssignPlayer = QAssignPlayer.assignPlayer;
		QTeam qTeam = QTeam.team;
		QTeamInfo qTeamInfo = QTeamInfo.teamInfo;
		QPlayer qPlayer = QPlayer.player;
		QPlayerCard qPlayerCard = QPlayerCard.playerCard;
		QPlayerPosition qPlayerPosition = QPlayerPosition.playerPosition;
		
		BooleanBuilder builder = new BooleanBuilder();
		if(input != null) {
			if(input.getTeamCode() != null && input.getTeamCode().length() > 0) {
				builder.and(qTeam.code.eq(input.getTeamCode()));
			}
			if(input.getPlayerCode() != null && input.getPlayerCode().length() > 0) {
				builder.and(qPlayer.code.eq(input.getPlayerCode()));
			}
		}
		log.debug("### INPUT: {}", input);
		
		JPAQuery<AssignPlayer> query = jpaQueryFactory.selectFrom(qAssignPlayer);
		
		query
			.innerJoin(qAssignPlayer.team, qTeam)
				.innerJoin(qAssignPlayer.team.info, qTeamInfo)
			.innerJoin(qAssignPlayer.player, qPlayer)
				.leftJoin(qAssignPlayer.player.card, qPlayerCard)
				.leftJoin(qAssignPlayer.player.position, qPlayerPosition)
			.where(builder)
			.orderBy(qAssignPlayer.player.age.asc());
		
		return query.transform(
					GroupBy.groupBy(qAssignPlayer.team.code, qAssignPlayer.player.code).list(
							new QAssignPlayerRDto(
									new QTeamRDto(
											qTeam.code, 
											qTeam.name,
											new QTeamInfoRDto(
													qTeamInfo.ownerName, 
													qTeamInfo.mobile, 
													qTeamInfo.address
											)
									),
									new QPlayerRDto(
											qPlayer.code, 
											qPlayer.name, 
											qPlayer.age, 
											qPlayer.created, 
											qPlayer.updated, 
											new QPlayerCardRDto(
													qPlayerCard.cardNo, 
													qPlayerCard.brandNm
											), 
											GroupBy.list(
													new QPlayerPositionRDto(
															qPlayerPosition.code, 
															qPlayerPosition.career
													)
											)
									),
									qAssignPlayer.backNo,
									qAssignPlayer.nickName
							)
					)
		);
	}
	
	
}

