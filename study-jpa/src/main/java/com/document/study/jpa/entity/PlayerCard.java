package com.document.study.jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "player_card")
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class PlayerCard {
	
	@ToString.Exclude
	@JsonBackReference
	@Id @MapsId 
	@OneToOne
    private Player player;
	
	private String cardNo;
	
	private String brandNm;
	
	@Builder
    protected PlayerCard(Player player, String cardNo, String brandNm) {
    	this.player = player;
    	this.cardNo = cardNo;
        this.brandNm = brandNm;
    }
    
    public void changeCardNo(String cardNo) {
    	this.cardNo = cardNo;
    }
    
    public void changeBrandNm(String brandNm) {
    	this.brandNm = brandNm;
    }
    
}
