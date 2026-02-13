package jpabasic.reserve.domain.relation.entity;

import com.google.gson.Gson;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Team {
	
    @Id
    private String code;
    
    private String name;
    
    
    protected Team() {}
    public Team(String code, String name) {
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