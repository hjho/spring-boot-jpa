package com.document.study.jpa.business.data;

import com.document.study.jpa.embed.Position;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class PlayerPositionRDto {
	
	private Position code;
    
    private Long career;
    
    @QueryProjection
    public PlayerPositionRDto(Position code, Long career) {
    	this.code = code;
    	this.career = career;
    }
    
}
