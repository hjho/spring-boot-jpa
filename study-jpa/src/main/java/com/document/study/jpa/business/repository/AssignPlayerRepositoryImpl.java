package com.document.study.jpa.business.repository;

import java.util.List;

import com.document.study.jpa.entity.AssignPlayer;
import com.document.study.jpa.entity.QAssignPlayer;
import com.document.study.jpa.entity.QPlayer;
import com.document.study.jpa.entity.QTeam;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AssignPlayerRepositoryImpl implements AssignPlayerRepositoryCustom {
	
	
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<AssignPlayer> findQueryDslAll() {
		return jpaQueryFactory.selectFrom(QAssignPlayer.assignPlayer).fetch();
	}

	@Override
	public AssignPlayer findQueryDslOne(String team, String player) {
		QAssignPlayer assignPlayer = QAssignPlayer.assignPlayer;
		
		JPAQuery<AssignPlayer> query = jpaQueryFactory.selectFrom(assignPlayer);
		
		query
			.where(assignPlayer.team.code.eq(team)
				.and(assignPlayer.player.code.eq(player)));
		
		return query.fetchOne();
	}

	@Override
	public List<AssignPlayer> findQueryDslJoin() {
		QAssignPlayer assignPlayer = QAssignPlayer.assignPlayer;
		QTeam team = QTeam.team;
		QPlayer player = QPlayer.player;
		
		JPAQuery<AssignPlayer> query = jpaQueryFactory.selectFrom(assignPlayer);
		
		query
			.innerJoin(QTeam.team, team)
			.innerJoin(QPlayer.player, player)
			.fetchJoin()	// 성능 최적화를 위한 페치 조인
			.where(assignPlayer.team.code.eq("T01"));
		
		return query.fetch();
	}

	@Override
	public List<AssignPlayer> findQueryDslDynamic(String team, String player) {
		QAssignPlayer qAssignPlayer = QAssignPlayer.assignPlayer;
		QTeam qTeam = QTeam.team;
		QPlayer qPlayer = QPlayer.player;
		
		JPAQuery<AssignPlayer> query = jpaQueryFactory.selectFrom(qAssignPlayer);
		
		BooleanBuilder builder = new BooleanBuilder();
		if(team != null && team.length() > 0) {
			builder.and(qTeam.code.eq(team));
		}
		if(player != null && player.length() > 0) {
			builder.and(qPlayer.code.eq(player));
		}
		
		query
			.innerJoin(QTeam.team, qTeam)
			.innerJoin(QPlayer.player, qPlayer)
			.fetchJoin()
			.where(builder);
		
		return query.fetch();
	}
	
	
}

