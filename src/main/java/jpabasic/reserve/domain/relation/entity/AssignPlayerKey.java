package jpabasic.reserve.domain.relation.entity;

import java.io.Serializable;


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
    
}
