package com.document.study.jpa.business.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.document.study.jpa.embed.Gender;
import com.document.study.jpa.entity.Manager;


public interface ManagerRepository extends CrudRepository<Manager, Long> {

	/* like name */
	int deleteByNameStartingWith(String name);
	
	/* equals */
	Manager findByName(String name);
	
	/* equals and  */
	// m1_0.gender=? 
	// and m1_0.is_foreigner=?
	List<Manager> findByGenderAndForeigner(Gender gender, boolean foreigner);
		
	/* like */ 
	// m1_0.name like ? escape '\\'
	List<Manager> findByNameLike(String name);
	List<Manager> findByNameContaining(String name);
	// m1_0.name not like ? escape '\\'
	List<Manager> findByNameNotContaining(String name);
	List<Manager> findByNameStartingWith(String name);
	List<Manager> findByEmailEndingWith(String email);
	// upper(m1_0.email) like upper(?) escape '\\'
	List<Manager> findByEmailEndingWithIgnoreCase(String email);
	
	/* boolean */
	// m1_0.email is null
	List<Manager> findByBirthdayIsNull();
	// m1_0.is_foreigner=false
	List<Manager> findByForeignerFalse();
	
	/* between */
	// m1_0.birthday between ? and ?
	List<Manager> findByBirthdayBetween(LocalDate a, LocalDate b);
	
	// m1_0.birthday<?
	List<Manager> findByBirthdayBefore(LocalDate a);
	// m1_0.birthday>?
	List<Manager> findByBirthdayAfter(LocalDate a);
	
	// m1_0.birthday<=?
	List<Manager> findByBirthdayLessThanEqual(LocalDate a);
	// m1_0.birthday>?
	List<Manager> findByBirthdayGreaterThan(LocalDate a);
	
	
}
