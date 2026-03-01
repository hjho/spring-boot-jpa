package com.document.study.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.document.study.jpa.entity.AssignPlayer;
import com.document.study.jpa.entity.AssignPlayerView;
import com.document.study.jpa.immutable.AssignPlayerVO;
import com.document.study.jpa.repository.AssignPlayerRepository;
import com.document.study.jpa.repository.AssignPlayerViewRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Transactional(readOnly = true)
@Service
@Slf4j
@RequiredArgsConstructor
public class AssignPlayerService {
	
	/**
	 * {@link com.document.study.jpa.repository.AssignPlayerRepositoryImpl AssignPlayerRepositoryImpl.java}
	 */
	private final AssignPlayerRepository assignPlayerRepository;
	
	private final AssignPlayerViewRepository assignPlayerViewRepository;

	
	public List<AssignPlayer> findQueryAll() {
		return assignPlayerRepository.findQueryAll();
	}

	public AssignPlayer findQueryOne(String team, String player) {
		return assignPlayerRepository.findQueryOne(team, player);
	}

	public List<AssignPlayerVO> findQueryJoin() {
		List<AssignPlayer> list = assignPlayerRepository.findQueryJoin();
		
		List<AssignPlayerVO> list2 = new ArrayList<>();
		
		for (AssignPlayer assignPlayer : list) {
			list2.add(new AssignPlayerVO(assignPlayer));
		}
		// org.springframework.http.converter.HttpMessageNotWritableException: Could not write JSON: Cannot lazily initialize collection of role
		log.debug("## LIST: {}", list2);
		return list2;
	}
	
	public List<AssignPlayerVO> findQueryDynamic(String team, String player) {
		List<AssignPlayer> list = assignPlayerRepository.findQueryDynamic(team, player);
		
		List<AssignPlayerVO> list2 = new ArrayList<>();
		
		for (AssignPlayer assignPlayer : list) {
			log.debug("## POSTION: {}", assignPlayer.getPlayer().getPosition());
			list2.add(new AssignPlayerVO(assignPlayer));
		}
		log.debug("## LIST: {}", list2.size());
		return list2;
	}
	
	
	
	

	public List<AssignPlayerView> findSubSelect() {
		return assignPlayerViewRepository.findAll();
	}
}
