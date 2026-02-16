package jpabasic.reserve.domain.relation.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Team {
	
    @Id
    private String code;
    
    private String name;
    
    @OneToOne(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    private TeamInfo info;
    
    @OneToMany(mappedBy = "team", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private List<AssignPlayer> plyers;
    
    protected Team() {}
    public Team(String code, String name) {
    	this.code = code;
        this.name = name;
        this.info = new TeamInfo(this);
    }
    
    public void changeName(String name) {
    	this.name = name;
    }
    public void initTeamInfo(String ownerName, String mobile, String address) {
    	this.info.initValue(ownerName, mobile, address);
    }
    
    public String getCode() {
    	return code;
    }
    public String getName() {
    	return name;
    }
    public TeamInfo getInfo() {
    	return info;
    }
    
    public List<AssignPlayer> getPlayers() {
    	return plyers;
    }

    @Override
    public String toString() {
    	Map<String, Object> map = new HashMap<>();
    	map.put("code", code);
    	map.put("name", name);
        return new Gson().toJson(map);
    }
}