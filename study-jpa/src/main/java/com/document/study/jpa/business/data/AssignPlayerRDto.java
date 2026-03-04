package com.document.study.jpa.business.data;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class AssignPlayerRDto {
	
	private TeamRDto team;
	
	private PlayerRDto player;
	
	private Long backNo;
	
	private String nickName;
	
	
	@QueryProjection
	public AssignPlayerRDto(TeamRDto team, PlayerRDto player, Long backNo, String nickName) {
		this.team = team;
		this.player = player;
		this.backNo = backNo;
		this.nickName = nickName;
	}
	
}
