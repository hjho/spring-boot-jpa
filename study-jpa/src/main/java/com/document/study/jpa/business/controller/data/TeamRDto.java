package com.document.study.jpa.business.controller.data;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamRDto {
    
    private String code;
    
    private String name;
    
    private TeamInfoRDto info;
    
    
    @QueryProjection
    public TeamRDto(String code, String name, TeamInfoRDto info) {
    	this.code = code;
    	this.name = name;
    	this.info = info;
    }
    
}
