package jpabasic.reserve.domain.relation.entity;

import java.io.Serializable;
import java.util.Objects;


public class AssignPlayerKey implements Serializable {
	
	private static final long serialVersionUID = -206261004591629092L;
	
	private String team;
	
	private String player;
	
    protected AssignPlayerKey() {}
    
    public AssignPlayerKey(String team, String player) {
    	this.team = team;
    	this.player = player;
    }
    
    public String getTeam() {
    	return team;
    }
    
    public String getPlayer() {
    	return player;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssignPlayerKey that = (AssignPlayerKey) o;
        return Objects.equals(team, that.team) && Objects.equals(player, that.player);
    }

    @Override	
    public int hashCode() {
        return Objects.hash(team, player);
    }
    
}
