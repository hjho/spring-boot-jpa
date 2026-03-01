package com.document.study.jpa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.document.study.jpa.entity.AssignPlayer;
import com.document.study.jpa.immutable.AssignPlayerVO;
import com.document.study.jpa.service.AssignPlayerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/assign-player")
@RequiredArgsConstructor
public class AssignPlayerContoller {
	
	final HttpStatusCode CODE_200 = HttpStatusCode.valueOf(HttpStatus.OK.value());
	
	final HttpStatusCode CODE_500 = HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()); 
	
	private final AssignPlayerService assignPlayerService;
	
	
	@GetMapping("/find")
	public ResponseEntity<List<AssignPlayer>> findQueryAll() {
		return new ResponseEntity<List<AssignPlayer>>(assignPlayerService.findQueryAll(), CODE_200);
	}
	
	@GetMapping("/find/{team}/{player}")
	public ResponseEntity<AssignPlayer> findQueryOne(@PathVariable String team, @PathVariable String player) {
		return new ResponseEntity<AssignPlayer>(assignPlayerService.findQueryOne(team, player), CODE_200);
	}
	
	@GetMapping("/find/join")
	public ResponseEntity<List<AssignPlayerVO>> findQueryJoin() {
		return new ResponseEntity<List<AssignPlayerVO>>(assignPlayerService.findQueryJoin(), CODE_200);
	}
	
	@GetMapping("/find/dynamic")
	public ResponseEntity<List<AssignPlayerVO>> findQueryDynamic(
			@RequestParam(required = false) String team, @RequestParam(required = false) String player
	) {
		return new ResponseEntity<List<AssignPlayerVO>>(assignPlayerService.findQueryDynamic(team, player), CODE_200);
	}
	
}
