package com.document.study.jpa.entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Persistable;

import com.document.study.jpa.embed.Position;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


// DB TABLE: player(code, name, age, created, updated)
@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Player implements Persistable<String> {
	
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
    
    @JsonManagedReference
    // optional = true 이므로 left join 으로 동작.
    @OneToOne(mappedBy = "player", cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REMOVE}, optional = true)
    private PlayerCard card;
    
    @JsonManagedReference
    // fetch = FetchType.LAZY 이므로 값을 불러올 때 조회.
    @OneToMany(mappedBy = "player", cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<PlayerPosition> position = new ArrayList<>();
    
    @Builder
    public Player(String code, String name, Long age) {
    	this.code = code;
        this.name = name;
        this.age = age;
        this.created = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
    }
    
    public void changeName(String name) {
    	this.name = name;
    	this.updated = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
    }
    
    public void setCard(String cardNo, String brandNm) {
    	this.card = PlayerCard.builder().player(this).cardNo(cardNo).brandNm(brandNm).build();
    }
    
    public void setPosition(List<PlayerPosition> position) {
    	this.position.clear();
    	this.position.addAll(position);
    }
    
    public PlayerPosition initPosition(Position code, Long career) {
    	return PlayerPosition.builder().player(this).code(code).career(career).build();
    }

    
    @Transient
    private boolean isNew = true;
    
	@Override
	public @Nullable String getId() {
		return this.code;
	}

	@JsonIgnore
	@Override
	public boolean isNew() {
		return isNew;
	}
	
	@PostLoad
    @PrePersist
    void markNotNew() {
        this.isNew = false;
    }
}
