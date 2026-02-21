package com.document.study.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Table(name = "test_team_info")
public class TestTeamInfo {
	
	@Id 
	@OneToOne
	@MapsId 
    private TestTeam team;
	
    private String ownerName;
	
	private String mobile;
	
	private String address;
    
	@Builder
    protected TestTeamInfo(TestTeam team) {
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