package com.document.study.jpa.immutable;

import org.springframework.data.annotation.Immutable;

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
public class TeamReadOnly {
    
	@Id
    private String code;
    
    private String name;
    
    private String ownerName;
	
	private String mobile;
	
	private String address;
	
	private Long   playerCnt;
    
}
