package com.document.study.jpa.business.controller.data;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class PlayerCardRDto {
	
	private String cardNo;
	
	private String brandNm;
	
	@QueryProjection
    public PlayerCardRDto(String cardNo, String brandNm) {
    	this.cardNo = cardNo;
        this.brandNm = brandNm;
    }
    
}
