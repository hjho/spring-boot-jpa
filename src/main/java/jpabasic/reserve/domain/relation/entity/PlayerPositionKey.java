package jpabasic.reserve.domain.relation.entity;

import java.io.Serializable;

import jpabasic.reserve.domain.relation.value.Position;


public class PlayerPositionKey implements Serializable  {
	
	private static final long serialVersionUID = -2737393025417013301L;

    private String player;
	
	private Position code;
    
    
    protected PlayerPositionKey() {}
    public PlayerPositionKey(String player, Position code) {
    	this.player = player;
    	this.code = code;
    }
    
    public String getPlayer() {
    	return this.player;
    }
    
    public Position getCode() {
    	return this.code;
    }
    
}
