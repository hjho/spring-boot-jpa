package com.document.study.jpa.business.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.document.study.jpa.business.data.TeamRVO;
import com.document.study.jpa.business.repository.TeamRepository;
import com.document.study.jpa.entity.Team;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamContoller {
	
	final HttpStatusCode CODE_200 = HttpStatusCode.valueOf(HttpStatus.OK.value());
	
	final HttpStatusCode CODE_500 = HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()); 
	
	private final TeamRepository teamRepository;
	
	
	@GetMapping("/find-all")
	public ResponseEntity<List<Team>> findAll() {
		
		List<Team> teams = teamRepository.findAll();
		
		return new ResponseEntity<List<Team>>(teams, CODE_200);
	}
	
	@GetMapping("/find/{code}")
	public ResponseEntity<Team> find(@PathVariable String code) {
		
		Optional<Team> team = teamRepository.findById(code);
		
		log.debug("## Team isPresent: {}", team.isPresent());
		
		if(team.isEmpty()) {
			return new ResponseEntity<Team>(CODE_500);
		}
		return new ResponseEntity<Team>(team.get(), CODE_200);
	}
	
	@GetMapping("/query")
	public ResponseEntity<List<TeamRVO>> query() {
		return new ResponseEntity<List<TeamRVO>>(teamRepository.findQuery(), CODE_200);
	}
	
	@GetMapping("/query/{code}")
	public ResponseEntity<TeamRVO> queryOne(@PathVariable String code) {
		return new ResponseEntity<TeamRVO>(teamRepository.findQueryOne(code), CODE_200);
	}

	
}
