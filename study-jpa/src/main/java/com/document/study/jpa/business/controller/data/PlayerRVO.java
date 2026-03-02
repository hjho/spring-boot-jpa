package com.document.study.jpa.business.controller.data;

import com.fasterxml.jackson.annotation.JsonRawValue;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerRVO {
    
    @Id
    private String code;
    
	private Long   age;
	
    private String name;
    
    private String cardNo;
    
    private String brandNm;
    
    @JsonRawValue
    private String positions;
    
}
