package jpabasic.reserve.domain.relation.entity;

import java.util.Set;

import com.google.gson.Gson;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
public class Player {
	
    @Id 
    private String code;
    
    @Column(nullable = false)
    private String name;
    
    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "player_code")
    private PlayerCard card;
    
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PlayerPosition> position;
    
    
    protected Player() {}
    public Player(String code, String name) {
    	this.code = code;
        this.name = name;
    }
    
    public String getCode() {
    	return code;
    }
    public String getName() {
    	return name;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
