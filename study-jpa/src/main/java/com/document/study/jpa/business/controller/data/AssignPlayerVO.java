package com.document.study.jpa.business.controller.data;

import com.document.study.jpa.entity.AssignPlayer;
import com.document.study.jpa.entity.Player;
import com.document.study.jpa.entity.Team;

import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public class AssignPlayerVO {
	
	private Team team;
	
	private Player player;
	
	private Long backNo;
	
	private String nickName;
	
	
    public AssignPlayerVO(AssignPlayer assignPlayer) {
    	this.team = assignPlayer.getTeam();
    	this.player = assignPlayer.getPlayer();
    	this.backNo = assignPlayer.getBackNo();
        this.nickName = assignPlayer.getNickName();
    }
    
}
