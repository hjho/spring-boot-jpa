package com.document.study.jpa.business.specific;

import org.springframework.data.jpa.domain.Specification;

import com.document.study.jpa.entity.PersistUser;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class UserNameSpecification implements Specification<PersistUser> {

	private static final long serialVersionUID = 1L;
	
	private final String value;

    public UserNameSpecification(String value) {
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<PersistUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return cb.like(root.get("name"), "%" + value + "%");
    }
}