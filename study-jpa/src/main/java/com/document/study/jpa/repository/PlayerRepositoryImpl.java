package com.document.study.jpa.repository;

import com.document.study.jpa.entity.Player;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayerRepositoryImpl implements PlayerRepositoryCustom {

	
	private final EntityManager entityManager;
	
	@Override
	public void persist(Player player) {
		entityManager.persist(player);
	}

}
