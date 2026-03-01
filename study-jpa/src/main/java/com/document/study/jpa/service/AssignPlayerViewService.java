package com.document.study.jpa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.document.study.jpa.entity.AssignPlayerView;
import com.document.study.jpa.repository.AssignPlayerViewRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssignPlayerViewService {
	
	private final AssignPlayerViewRepository assignPlayerViewRepository;

	
	@Transactional(readOnly = true)
	public List<AssignPlayerView> findAll() {
		return assignPlayerViewRepository.findAll();
	}
	
	
	
}
