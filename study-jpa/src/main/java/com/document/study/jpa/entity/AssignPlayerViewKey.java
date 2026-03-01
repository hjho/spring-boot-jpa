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
public class AssignPlayerViewKey implements Serializable {
	
	private static final long serialVersionUID = -206261004591629092L;
	
	private String teamCode;
	
	private String playerCode;
	
}
