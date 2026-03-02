package com.document.study.jpa.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.document.study.jpa.embed.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.TableGenerator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Manager {
	
	@Id
	@TableGenerator(
            name = "manager_id",
            table = "id_seq", // 키 관리 테이블명
            pkColumnName = "entity",  // 테이블에서 사용할 컬럼명1
            pkColumnValue = "manager", // 테이블에서 사용할 컬럼값1
            valueColumnName = "nextval", // 테이블에서 사용할 컬럼명2
            initialValue = 0, 			// 테이블에서 사용할 컬럼값2
            allocationSize = 1 // 한번에 1개씩 증가
    )
    @GeneratedValue(generator = "manager_id", strategy = GenerationType.TABLE)
	private Long id;
	
	private String name;
	
	private LocalDate birthday;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column(name = "is_foreigner")
	private Boolean foreigner;
	
	private String email;
	
	@CreatedDate
	private LocalDateTime createDate;
	
	@CreatedBy
	private String createdBy;
	
	@LastModifiedDate
	private LocalDateTime lastModifiedDate;
	
	@LastModifiedBy
	private String lastModifiedBy;
	
	public void changeEmail(String email) {
		this.email = email;
	}
	
	public void changeBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	
    public Manager(String name, String email, LocalDate birthday, Gender gender, boolean foreigner) {
		this.name = name;
		this.email = email;
		this.birthday = birthday;
		this.gender = gender;
		this.foreigner = foreigner;
    }
    
    public Manager(Gender gender) {
    	this.gender = gender;
    }
	
}
