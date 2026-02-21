package jpabasic.reserve.domain.relation.entity;

import java.io.Serializable;
import java.util.Objects;

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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerPositionKey that = (PlayerPositionKey) o;
        return Objects.equals(player, that.player) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, code);
    }
    
}
