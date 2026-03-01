package com.document.study.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.document.study.jpa.entity.PersistUser;

public interface PersistUserRepository extends JpaRepository<PersistUser, String> {

	
	List<PersistUser> findAll(Specification<PersistUser> spec);
	
}
