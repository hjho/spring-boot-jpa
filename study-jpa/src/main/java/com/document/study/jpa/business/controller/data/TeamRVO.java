package com.document.study.jpa.business.controller.data;

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
public class TeamRVO {
    
	@Id
    private String code;
    
    private String name;
    
    private String ownerName;
	
	private String mobile;
	
	private String address;
	
	private Long   playerCnt;
    
}
