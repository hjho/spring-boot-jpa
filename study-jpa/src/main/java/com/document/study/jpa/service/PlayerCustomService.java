package com.document.study.jpa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.document.study.jpa.entity.Player;
import com.document.study.jpa.immutable.PlayerReadOnly;
import com.document.study.jpa.repository.PlayerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Transactional(readOnly = true)
@Service
@Slf4j
@RequiredArgsConstructor
public class PlayerCustomService {
	
	private final PlayerRepository playerRepository;


	public List<Player> jpql() {
		return playerRepository.findJpql();
	}
	public Player jpqlOne(String code) {
		return playerRepository.findJpqlOne(code);
	}
	
	
	public List<PlayerReadOnly> query() {
		return playerRepository.findQuery();
	}
	public PlayerReadOnly queryOne(String code) {
		return playerRepository.findQueryOne(code);
	}
	
	
	public List<Player> criteria() {
		List<Player> players = playerRepository.findCriteria();
		for (Player player : players) {
			log.debug("## Player: {}", player);
		}
		return players;
	}
	public Player criteriaOne(String code) {
		Player player = playerRepository.findCriteriaOne(code);
		log.debug("## Player: {}", player);
		return player;
	}
	
	
}
