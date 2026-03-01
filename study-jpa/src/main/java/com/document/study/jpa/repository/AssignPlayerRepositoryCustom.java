package com.document.study.jpa.repository;

import java.util.List;

import com.document.study.jpa.entity.AssignPlayer;

public interface AssignPlayerRepositoryCustom {
	
	List<AssignPlayer> findQueryAll();
	
	AssignPlayer findQueryOne(String team, String player);
	
	List<AssignPlayer> findQueryJoin();
	
	List<AssignPlayer> findQueryDynamic(String team, String player);
	
}
