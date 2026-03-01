package com.document.study.jpa.entity;

import java.util.List;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@IdClass(AssignPlayerViewKey.class)
@Entity
@Immutable
@Subselect("""
		SELECT team.team_code 
		     , team.team_name 
		     , team.address 
		     , team.mobile 
		     , team.owner_name 
		     , player.player_code
		     , player.player_name 
		     , player.age
		     , player.card_no 
		     , player.brand_nm 
		     , player.career 
		     , player.positions
		     , assign.back_no
		     , assign.nick_name 
		  FROM jpabegin.assign_player assign
		 inner join (
				select t.code as team_code
				     , t.name as team_name 
				     , ti.address 
				     , ti.mobile 
				     , ti.owner_name 
				  from jpabegin.team t
				 inner join jpabegin.team_info ti
				    on t.code = ti.team_code 
		     ) team
		    on assign.team_code = team.team_code
		 inner join (
				select p.code as player_code
				     , p.name as player_name
				     , p.age 
				     , pc.card_no 
				     , pc.brand_nm 
				     , pp.career 
				     , JSON_ARRAYAGG(pp.code) AS positions
				  from jpabegin.player p
				  left join jpabegin.player_card pc
				    on p.code = pc.player_code 
				  left join jpabegin.player_position pp 
				    on p.code = pp.player_code
				 group by player_code
		     ) player
		    on assign.player_code = player.player_code
		""")
@Synchronize({"team", "player"}) // 데이터 동기화 대상 테이블
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AssignPlayerView {
	
	@Id
	@Column(name = "team_code")
    private String teamCode;
    
	@Column(name = "team_name")
    private String teamName;
    
    @Column(name = "owner_name")
    private String ownerName;
	
	private String mobile;
	
	private String address;
	
	
	@Id
	@Column(name = "player_code")
    private String playerCode;
    
	@Column(name = "player_name")
    private String playerName;
    
    private Long   age;
    
	private String cardNo;
	
	private String brandNm;
	
	private List<String> positions;
    
    private Long career;
    
    
    @Column(name = "back_no")
	private Long backNo;
	
	@Column(name = "nick_name")
	private String nickName;
	
}
