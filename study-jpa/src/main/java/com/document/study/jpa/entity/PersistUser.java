package com.document.study.jpa.entity;

import java.time.LocalDateTime;

import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersistUser implements Persistable<String> {
	
    @Id
    private String email;
    
    private String name;

    private LocalDateTime createDate;

    @Transient
    private boolean isNew = true;

    public PersistUser(String email, String name, LocalDateTime createDate) {
        this.email = email;
        this.name = name;
        this.createDate = createDate;
    }

    public void changeName(String newName) {
        this.name = newName;
    }

    @Override
    public String getId() {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isNew() {
        return isNew;
    }

    @PostLoad
    @PrePersist
    void markNotNew() {
    	// isNew() 를 구현하여, save() 호출 시 persist 인지 merge 인지 판단 함수 구현. 
    	// 저장 후 혹은 DB에서 불러온 후엔 false 로 변경.
        this.isNew = false;
    }
    
    /* markNotNew() 대신 아래 방향으로 구현 에시 코드.
    @CreatedDate
    private LocalDateTime createdAt; // 생성 시점에 자동 채워짐

    @Override
    public boolean isNew() {
        // createdAt이 null이면 아직 DB에 저장되지 않은 '새 객체'로 판단
        return createdAt == null;
    }
     */
}