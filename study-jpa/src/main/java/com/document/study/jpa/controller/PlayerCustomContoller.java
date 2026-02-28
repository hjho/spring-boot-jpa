package com.document.study.jpa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.document.study.jpa.entity.Player;
import com.document.study.jpa.immutable.PlayerReadOnly;
import com.document.study.jpa.service.PlayerCustomService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/player-custom")
@RequiredArgsConstructor
public class PlayerCustomContoller {
	
	final HttpStatusCode CODE_200 = HttpStatusCode.valueOf(HttpStatus.OK.value());
	
	final HttpStatusCode CODE_500 = HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()); 
	
	private final PlayerCustomService playerCustomService;
	
	
	@GetMapping("/jpql")
	public ResponseEntity<List<Player>> jpql() {
		return new ResponseEntity<List<Player>>(playerCustomService.jpql(), CODE_200);
	}
	@GetMapping("/jpql/{code}")
	public ResponseEntity<Player> jpqlOne(@PathVariable String code) {
		return new ResponseEntity<Player>(playerCustomService.jpqlOne(code), CODE_200);
	}
	
	
	@GetMapping("/criteria")
	public ResponseEntity<List<Player>> criteria() {
		return new ResponseEntity<List<Player>>(playerCustomService.criteria(), CODE_200);
	}
	@GetMapping("/criteria/{code}")
	public ResponseEntity<Player> criteriaOne(@PathVariable String code) {
		return new ResponseEntity<Player>(playerCustomService.criteriaOne(code), CODE_200);
	}
	
	
	@GetMapping("/query")
	public ResponseEntity<List<PlayerReadOnly>> query() {
		return new ResponseEntity<List<PlayerReadOnly>>(playerCustomService.query(), CODE_200);
	}
	@GetMapping("/query/{code}")
	public ResponseEntity<PlayerReadOnly> queryOne(@PathVariable String code) {
		return new ResponseEntity<PlayerReadOnly>(playerCustomService.queryOne(code), CODE_200);
	}
	
	
	
}
