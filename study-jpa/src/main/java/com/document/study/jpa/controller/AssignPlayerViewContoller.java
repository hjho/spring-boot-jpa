package com.document.study.jpa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.document.study.jpa.entity.AssignPlayerView;
import com.document.study.jpa.service.AssignPlayerViewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/assign-player-view")
@RequiredArgsConstructor
public class AssignPlayerViewContoller {
	
	final HttpStatusCode CODE_200 = HttpStatusCode.valueOf(HttpStatus.OK.value());
	
	final HttpStatusCode CODE_500 = HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()); 
	
	private final AssignPlayerViewService assignPlayerViewService;
	
	
	@GetMapping("/find-all")
	public ResponseEntity<List<AssignPlayerView>> findAll() {
		return new ResponseEntity<List<AssignPlayerView>>(assignPlayerViewService.findAll(), CODE_200);
	}
	
}
