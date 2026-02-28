package com.document.study.jpa.entity;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AssignPlayerKey implements Serializable {
	
	private static final long serialVersionUID = -206261004591629092L;
	
	private String team;
	
	private String player;
	
}
