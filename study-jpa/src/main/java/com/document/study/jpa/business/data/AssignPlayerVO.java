package com.document.study.jpa.business.data;

import java.util.ArrayList;

import com.document.study.jpa.entity.AssignPlayer;
import com.document.study.jpa.entity.Player;
import com.document.study.jpa.entity.Team;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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
        // LAZY.
        this.player.setPosition(new ArrayList<>(assignPlayer.getPlayer().getPosition()));
    }
    
}
