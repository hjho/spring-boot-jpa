package com.document.study.jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@IdClass(AssignPlayerKey.class)
@Entity
@Table(name = "assign_player")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AssignPlayer {
	
	@JsonBackReference
	@Id
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@MapsId
	private Team team;
	
	@JsonBackReference
	@Id
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@MapsId
	private Player player;
	
	@Column(name = "back_no")
	private Long backNo;
	
	@Column(name = "nick_name")
	private String nickName;
	
	@Builder
    public AssignPlayer(Team team, Player player, Long backNo, String nickName) {
    	this.team = team;
    	this.player = player;
    	this.backNo = backNo;
        this.nickName = nickName;
    }
    
    public void changeBackNo(Long backNo) {
    	this.backNo = backNo;
    }
    public void changeNickName(String nickName) {
    	this.nickName = nickName;
    }
}
