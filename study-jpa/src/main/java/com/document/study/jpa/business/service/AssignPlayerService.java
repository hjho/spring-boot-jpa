package com.document.study.jpa.business.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.document.study.jpa.business.controller.data.AssignPlayerVO;
import com.document.study.jpa.business.repository.AssignPlayerRepository;
import com.document.study.jpa.entity.AssignPlayer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Transactional(readOnly = true)
@Service
@Slf4j
@RequiredArgsConstructor
public class AssignPlayerService {
	
	/**
	 * {@link com.document.study.jpa.business.repository.AssignPlayerRepositoryImpl AssignPlayerRepositoryImpl.java}
	 */
	private final AssignPlayerRepository assignPlayerRepository;
	
	
	public List<AssignPlayer> findQueryDslAll() {
		return assignPlayerRepository.findQueryDslAll();
	}

	public AssignPlayer findQueryDslOne(String team, String player) {
		return assignPlayerRepository.findQueryDslOne(team, player);
	}

	public List<AssignPlayerVO> findQueryDslJoin() {
		List<AssignPlayer> list = assignPlayerRepository.findQueryDslJoin();
		
		List<AssignPlayerVO> list2 = new ArrayList<>();
		
		for (AssignPlayer assignPlayer : list) {
			list2.add(new AssignPlayerVO(assignPlayer));
		}
		// org.springframework.http.converter.HttpMessageNotWritableException: Could not write JSON: Cannot lazily initialize collection of role
		log.debug("## LIST: {}", list2);
		return list2;
	}
	
	public List<AssignPlayerVO> findQueryDslDynamic(String team, String player) {
		List<AssignPlayer> list = assignPlayerRepository.findQueryDslDynamic(team, player);
		
		List<AssignPlayerVO> list2 = new ArrayList<>();
		
		for (AssignPlayer assignPlayer : list) {
			log.debug("## POSTION: {}", assignPlayer.getPlayer().getPosition());
			list2.add(new AssignPlayerVO(assignPlayer));
		}
		log.debug("## LIST: {}", list2.size());
		return list2;
	}
	
	
}
