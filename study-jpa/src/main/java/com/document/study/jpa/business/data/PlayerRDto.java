package com.document.study.jpa.business.data;

import java.util.ArrayList;
import java.util.List;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class PlayerRDto {
	
    private String code;
    
    private String name;
    
    private Long   age;
    
    private String created;
    
    private String updated;
    
    private PlayerCardRDto card;
    
    private List<PlayerPositionRDto> position = new ArrayList<>();
    
    @QueryProjection
    public PlayerRDto(String code, String name, Long age, String created, String updated, PlayerCardRDto card, List<PlayerPositionRDto> position) {
    	this.code = code;
        this.name = name;
        this.age = age;
        this.created = created;
        this.updated = updated;
        this.card = card;
        this.position = position;
    }
    
}
