package com.document.study.jpa.business.specific;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

import com.document.study.jpa.entity.PersistUser;

public class UserSpecs {
	
    public static Specification<PersistUser> nameLike(String value) {
        return (root, query, cb) -> cb.like(root.get("name"), "%" + value + "%");
    }

    public static Specification<PersistUser> createdAfter(LocalDateTime dt) {
        return (root, query, cb) -> cb.greaterThan(root.get("createDate"), dt);
    }
    
    
    public static Specification<PersistUser> isNameTen() {
    	return (root, query, cb) -> cb.equal(root.get("name"), "범생이10");
    }
    
}