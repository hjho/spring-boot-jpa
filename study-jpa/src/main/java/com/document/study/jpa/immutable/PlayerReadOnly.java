package com.document.study.jpa.immutable;

import org.springframework.data.annotation.Immutable;

import com.fasterxml.jackson.annotation.JsonRawValue;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Immutable
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerReadOnly {
    
    @Id
    private String code;
    
	private Long   age;
	
    private String name;
    
    private String cardNo;
    
    private String brandNm;
    
    @JsonRawValue
    private String positions;
    
}
