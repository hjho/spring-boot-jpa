package jpabasic.reserve.domain.relation.entity;

import com.google.gson.Gson;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;


@IdClass(AssignPlayerKey.class)
@Entity
@Table(name = "assign_player")
public class AssignPlayer {
	
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId
	private Team team;
	
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId
	private Player player;
	
	@Column(name = "back_no")
	private Long backNo;
	
	@Column(name = "nick_name")
	private String nickName;
	
	
    protected AssignPlayer() {}
    public AssignPlayer(Team team, Player player, Long backNo, String nickName) {
    	this.team = team;
    	this.player = player;
    	this.backNo = backNo;
        this.nickName = nickName;
    }
    
    public Team getTeam() {
    	return team;
    }
    
    public Player getPlayer() {
    	return player;
    }
    
    public Long getBackNo() {
    	return backNo;
    }
    
    public String getNickName() {
    	return nickName;
    }
    
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
