package jpabasic.reserve.domain.relation.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.google.gson.Gson;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "player_card")
// DB TABLE: player_card(card_no, brand_nm, player_code)
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
    protected PlayerCard(Player player, String cardNo, String brandNm) {
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
    
    public void changeCardNo(String cardNo) {
    	this.cardNo = cardNo;
    }
    
    public void changeBrandNm(String brandNm) {
    	this.brandNm = brandNm;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerCard that = (PlayerCard) o;
        return Objects.equals(player, that.player) && Objects.equals(cardNo, that.cardNo) && Objects.equals(brandNm, that.brandNm);
    }

    @Override	
    public int hashCode() {
        return Objects.hash(player, cardNo, brandNm);
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
