package com.document.study.jpa.business.repository;

import java.util.List;

import com.document.study.jpa.entity.Player;

public interface PlayerRepositoryCustom {
	
	void persist(Player player);
	
	
	List<Player> findJpql();
	
	Player findJpqlOne(String code);
	
	
	List<Player> findCriteria();
	
	Player findCriteriaOne(String code);
	
}
