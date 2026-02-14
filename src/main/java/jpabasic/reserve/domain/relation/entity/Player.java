package jpabasic.reserve.domain.relation.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jpabasic.reserve.domain.relation.value.Position;


@Entity
// DB TABLE: player(code, name, age, created, updated)
public class Player {
	
    @Id 
    private String code;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = true)
    private Long   age;
    
    // update 시, 값을 변경하더라도 update 안됨.
    @Column(nullable = false, updatable = false)
    private String created;
    
    // insert 시, 값을 입력하더라도 insert 안됨. 
    @Column(nullable = true, insertable = false)
    private String updated;
    
    // optional = true 이므로 left join 으로 동작.
    @OneToOne(mappedBy = "player", cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REMOVE}, optional = true)
    @JoinColumn(name = "player_code")
    private PlayerCard card;
    
    // fetch = FetchType.LAZY 이므로 값을 불러올 때 조회.
    @OneToMany(mappedBy = "player", cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<PlayerPosition> position;
    
    
    protected Player() {}
    public Player(String code, String name, Long age, String created, String updated) {
    	this.code = code;
        this.name = name;
        this.age = age;
        this.created = created;
        this.updated = updated;
    }
    
    
    public void changeName(String name, String date) {
    	this.name = name;
    	this.created = date;
    	this.updated = date;
    }
    
    public void setCard(String cardNo, String brandNm) {
    	this.card = new PlayerCard(this, cardNo, brandNm);
    }
    
    public PlayerPosition initPosition(Position code, Long career) {
    	return new PlayerPosition(this, code, career);
    }
    public void changePosition(List<PlayerPosition> position) {
    	this.position = position;
    }
    
    
    public String getCode() {
    	return code;
    }
    public String getName() {
    	return name;
    }
    public Long getAge() {
    	return age;
    }
    public String getCreated() {
    	return created;
    }
    public String getUpdated() {
    	return updated;
    }
    public PlayerCard getCard() {
    	return card;
    }
    public List<PlayerPosition> getPosition() {
    	return position;
    }

    @Override
    public String toString() {
    	Gson g = new Gson();
    	Map<String, Object> map = new HashMap<>();
    	map.put("code", code);
    	map.put("name", name);
    	map.put("age", age);
    	map.put("created", created);
    	map.put("updated", updated);
    	return g.toJson(map);
    }
}
