package com.document.study.jpa.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
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
@Table(name = "test_team")
public class TestTeam {
	
    @Id
    private String code;
    
    private String name;
    
    @OneToOne(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    private TestTeamInfo info;
    
    @Builder
    public TestTeam(String code, String name) {
    	this.code = code;
        this.name = name;
        this.info = TestTeamInfo.builder().team(this).build();
    }
    
    public void changeName(String name) {
    	this.name = name;
    }
    public void initTeamInfo(String ownerName, String mobile, String address) {
    	this.info.initValue(ownerName, mobile, address);
    }
    
}