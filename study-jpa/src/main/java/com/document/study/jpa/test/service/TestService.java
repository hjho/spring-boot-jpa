package com.document.study.jpa.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.document.study.jpa.entity.TestTeam;
import com.document.study.jpa.repository.TeamRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestService {

	private final TeamRepository team;
	
	
	public List<TestTeam> findAll() {
		return team.findAll();
	}
	
	public Optional<TestTeam> find(String code) {
		return team.findById(code);
	}
	
}
