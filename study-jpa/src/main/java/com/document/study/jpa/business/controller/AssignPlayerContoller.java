package com.document.study.jpa.business.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.document.study.jpa.business.controller.data.AssignPlayerVO;
import com.document.study.jpa.business.repository.AssignPlayerViewRepository;
import com.document.study.jpa.business.service.AssignPlayerService;
import com.document.study.jpa.entity.AssignPlayer;
import com.document.study.jpa.entity.AssignPlayerView;

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
	
	private final AssignPlayerViewRepository assignPlayerViewRepository;
	
	
	@GetMapping("/find-view")
	public ResponseEntity<List<AssignPlayerView>> findView() {
		return new ResponseEntity<List<AssignPlayerView>>(assignPlayerViewRepository.findAll(), CODE_200);
	}
	
	
	@GetMapping("/find")
	public ResponseEntity<List<AssignPlayer>> findQueryDslAll() {
		return new ResponseEntity<List<AssignPlayer>>(assignPlayerService.findQueryDslAll(), CODE_200);
	}
	
	@GetMapping("/find/{team}/{player}")
	public ResponseEntity<AssignPlayer> findQueryDslOne(@PathVariable String team, @PathVariable String player) {
		return new ResponseEntity<AssignPlayer>(assignPlayerService.findQueryDslOne(team, player), CODE_200);
	}
	
	@GetMapping("/find/join")
	public ResponseEntity<List<AssignPlayerVO>> findQueryDslJoin() {
		return new ResponseEntity<List<AssignPlayerVO>>(assignPlayerService.findQueryDslJoin(), CODE_200);
	}
	
	@GetMapping("/find/dynamic")
	public ResponseEntity<List<AssignPlayerVO>> findQueryDslDynamic(
			@RequestParam(required = false) String team, @RequestParam(required = false) String player
	) {
		return new ResponseEntity<List<AssignPlayerVO>>(assignPlayerService.findQueryDslDynamic(team, player), CODE_200);
	}
	
}
