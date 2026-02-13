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
@Table(name = "player_card")
public class PlayerCard {
	
	@Id 
	@OneToOne
	@MapsId
    private Player player;
	
	@Column(name = "card_no")
	private String cardNo;
	
	@Column(name = "brand_nm")
	private String brandNm;
	
	
    protected PlayerCard() {}
    public PlayerCard(Player player, String cardNo, String brandNm) {
    	this.player = player;
    	this.cardNo = cardNo;
        this.brandNm = brandNm;
    }
    
    public String getCardNo() {
    	return this.cardNo;
    }
    
    public String getBrandNm() {
    	return this.brandNm;
    }
    
    @Override
    public String toString() {
    	Map<String, Object> map = new HashMap<>();
    	map.put("playerCode", player.getCode());
    	map.put("cardNo", cardNo);
    	map.put("brandNm", brandNm);
        return new Gson().toJson(map);
    }
}
