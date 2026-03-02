package com.document.study.jpa.business.repository;

import java.util.List;

import com.document.study.jpa.entity.AssignPlayer;

public interface AssignPlayerRepositoryCustom {
	
	List<AssignPlayer> findQueryDslAll();
	
	AssignPlayer findQueryDslOne(String team, String player);
	
	List<AssignPlayer> findQueryDslJoin();
	
	List<AssignPlayer> findQueryDslDynamic(String team, String player);
	
}
