package com.document.study.jpa.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.document.study.jpa.embed.Gender;
import com.document.study.jpa.entity.Manager;

public interface ManagerPagingRepository extends JpaRepository<Manager, Long> {
	
	
	List<Manager> findByGenderOrderByBirthdayDesc(Gender gender);
	
	
	List<Manager> findTop10ByGenderOrderByBirthdayDesc(Gender gender);
	
	
}
