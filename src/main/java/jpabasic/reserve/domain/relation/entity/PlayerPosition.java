package jpabasic.reserve.domain.relation.entity;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jpabasic.reserve.domain.relation.value.Position;


@IdClass(PlayerPositionKey.class)
@Entity
@Table(name = "player_position")
public class PlayerPosition {
	
	@Id
	@ManyToOne
	@MapsId("player_code")
    private Player player;
	
	@Id
    @Column(name = "code")
	@Enumerated(EnumType.STRING)
	private Position code;
    
    @Column(name = "career")
    private Long career;
    
    
    protected PlayerPosition() {}
    protected PlayerPosition(Player player, Position code, Long career) {
    	this.player = player;
    	this.code = code;
    	this.career = career;
    }
    
    public Position getCode() {
    	return this.code;
    }
    
    public Long getCareer() {
    	return this.career;
    }
    
    public void changeCareer(Position code, Long career) {
    	if(this.code == code) {
    		this.career = career;
    	}
    }
    
    @Override
    public String toString() {
    	Map<String, Object> map = new HashMap<>();
    	map.put("playerCode", player.getCode());
    	map.put("code", code);
    	map.put("career", career);
        return new Gson().toJson(map);
    }
}
