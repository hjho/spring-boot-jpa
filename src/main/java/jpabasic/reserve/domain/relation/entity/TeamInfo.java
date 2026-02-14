package jpabasic.reserve.domain.relation.entity;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "team_info")
public class TeamInfo {
	
	// @MapsId: 외래 키를 기본 키로 매핑 + @Id
	// @JoinColumn: 자신만의 고유한 기본 키(@Id)를 가지고 있고, 부모 테이블을 참조하는 외래 키를 가질 때 사용합니다.
	
	@Id 
	@OneToOne
	@MapsId 
    private Team team;
	
	@Column(name = "owner_name")
    private String ownerName;
	
	// General directory number 대표번호
	@Column(name = "mobile")
	private String mobile;
	
	@Column(name = "address")
	private String address;
    
    protected TeamInfo() {}
    protected TeamInfo(Team team) {
    	this.team = team;
    }
    
    public void initValue(String ownerName, String mobile, String address) {
    	this.ownerName = ownerName;
    	this.mobile = mobile;
    	this.address = address;
    }
    
    public void changeOwnerName(String ownerName) {
    	this.ownerName = ownerName;
    }
    
    public void changeMobile(String mobile) {
    	this.mobile = mobile;
    }
    
    public String getOwnerName() {
    	return ownerName;
    }

    @Override
    public String toString() {
    	Map<String, Object> map = new HashMap<>();
    	map.put("teamCode", team.getCode());
    	map.put("ownerName", ownerName);
    	map.put("mobile", mobile);
    	map.put("address", address);
        return new Gson().toJson(map);
    }
}