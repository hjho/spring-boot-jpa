package com.document.study.jpa.entity;

import com.document.study.jpa.embed.Position;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@IdClass(PlayerPositionKey.class)
@Entity
@Table(name = "player_position")
@Getter
@ToString
@NoArgsConstructor
public class PlayerPosition {
	
	@ToString.Exclude
	@JsonBackReference
	@Id @JoinColumn(name = "player_code")
	@ManyToOne
    private Player player;
	
	@Id
	@Enumerated(EnumType.STRING)
	private Position code;
    
    private Long career;
    
    @Builder
    protected PlayerPosition(Player player, Position code, Long career) {
    	this.player = player;
    	this.code = code;
    	this.career = career;
    }
    
    public void changeCareer(Position code, Long career) {
    	if(this.code == code) {
    		this.career = career;
    	}
    }
}
