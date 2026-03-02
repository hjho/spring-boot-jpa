package com.document.study.jpa.business.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.document.study.jpa.business.controller.data.AssignPlayerPDto;
import com.document.study.jpa.business.controller.data.AssignPlayerRDto;
import com.document.study.jpa.business.controller.data.AssignPlayerVO;
import com.document.study.jpa.business.repository.AssignPlayerRepository;
import com.document.study.jpa.entity.AssignPlayer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssignPlayerService {
	
	/**
	 * AssignPlayerRepositoryImpl.java
	 */
	private final AssignPlayerRepository assignPlayerRepository;
	
	@Transactional(readOnly = true)
	public AssignPlayer findQueryDslOne(String team, String player) {
		return assignPlayerRepository.findQueryDslOne(team, player);
	}
	
	@Transactional(readOnly = true)
	public List<AssignPlayerVO> findQueryDslAll() {
		return assignPlayerRepository.findQueryDslAll();
	}

	
	@Transactional(readOnly = true)
	public List<AssignPlayerVO> findQueryDslJoin() {
		return assignPlayerRepository.findQueryDslJoin();
	}
	
	@Transactional(readOnly = true)
	public List<AssignPlayerRDto> findQueryDslDynamic(AssignPlayerPDto input) {
		return assignPlayerRepository.findQueryDslDynamic(input);
	}
	
	
}
