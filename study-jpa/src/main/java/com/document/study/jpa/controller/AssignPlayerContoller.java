package com.document.study.jpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.document.study.jpa.entity.AssignPlayer;
import com.document.study.jpa.entity.AssignPlayerKey;
import com.document.study.jpa.repository.AssignPlayerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/assign-player")
@RequiredArgsConstructor
public class AssignPlayerContoller {
	
	final HttpStatusCode CODE_200 = HttpStatusCode.valueOf(HttpStatus.OK.value());
	
	final HttpStatusCode CODE_500 = HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()); 
	
	private final AssignPlayerRepository assignPlayerRepository;
	
	
	@GetMapping("/find-all")
	public ResponseEntity<List<AssignPlayer>> findAll() {
		
		List<AssignPlayer> players = assignPlayerRepository.findAll();
		
		return new ResponseEntity<List<AssignPlayer>>(players, CODE_200);
	}
	
	@GetMapping("/find/{team}/{player}")
	public ResponseEntity<AssignPlayer> find(@PathVariable String team, @PathVariable String player) {
		
		Optional<AssignPlayer> player1 = assignPlayerRepository.findById(new AssignPlayerKey(team, player));
		
		log.debug("## AssignPlayer isPresent: {}", player1.isPresent());
		
		if(player1.isEmpty()) {
			return new ResponseEntity<AssignPlayer>(CODE_500);
		}
		return new ResponseEntity<AssignPlayer>(player1.get(), CODE_200);
	}
	
}
