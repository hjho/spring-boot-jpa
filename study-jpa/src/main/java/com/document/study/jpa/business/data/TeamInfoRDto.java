package com.document.study.jpa.business.data;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamInfoRDto {
    
    private String ownerName;
	
	private String mobile;
	
	private String address;
    
    @QueryProjection
    public TeamInfoRDto(String ownerName, String mobile, String address) {
    	this.ownerName = ownerName;
    	this.mobile = mobile;
    	this.address = address;
    }
    
}
