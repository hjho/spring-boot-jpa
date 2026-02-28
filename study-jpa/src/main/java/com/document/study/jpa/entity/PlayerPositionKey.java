package com.document.study.jpa.entity;

import java.io.Serializable;

import com.document.study.jpa.embed.Position;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerPositionKey implements Serializable  {
	
	private static final long serialVersionUID = -2737393025417013301L;

    private String player;
	
	private Position code;
    
}
