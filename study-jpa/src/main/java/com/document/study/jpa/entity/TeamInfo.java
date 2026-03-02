package com.document.study.jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class TeamInfo {
	
	@ToString.Exclude
	@JsonBackReference
	@Id 
	@OneToOne
	@MapsId 
    private Team team;
	
    private String ownerName;
	
	private String mobile;
	
	private String address;
    
	@Builder
    protected TeamInfo(Team team) {
    	this.team = team;
    }
    
    public void initValue(String ownerName, String mobile, String address) {
    	this.ownerName = ownerName;
    	this.mobile = mobile;
    	this.address = address;
    }
    public void changeOwnerName(String ownerName) {
    	this.ownerName = ownerName;
    }
    public void changeMobile(String mobile) {
    	this.mobile = mobile;
    }
    
}