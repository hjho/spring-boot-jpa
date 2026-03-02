package com.document.study.jpa.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Team {
	
    @Id
    private String code;
    
    private String name;
    
    @JsonManagedReference
    @OneToOne(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    private TeamInfo info;
    
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "team", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<AssignPlayer> plyers;
    
    @Builder
    public Team(String code, String name) {
    	this.code = code;
        this.name = name;
        this.info = TeamInfo.builder().team(this).build();
    }
    
    public void changeName(String name) {
    	this.name = name;
    }
    public void initTeamInfo(String ownerName, String mobile, String address) {
    	this.info.initValue(ownerName, mobile, address);
    }
}