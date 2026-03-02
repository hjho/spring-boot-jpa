package com.document.study.jpa.business.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.document.study.jpa.business.service.PersistUserService;
import com.document.study.jpa.entity.PersistUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/persist-user")
@RequiredArgsConstructor
public class PersistUserContoller {
	
	final HttpStatusCode CODE_200 = HttpStatusCode.valueOf(HttpStatus.OK.value());
	
	final HttpStatusCode CODE_500 = HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()); 
	
	private final PersistUserService persistUserService;
	
	
	@GetMapping("/persist")
	public ResponseEntity<Boolean> persist() {
		return new ResponseEntity<Boolean>(persistUserService.persist(), CODE_200);
	}
	
	@GetMapping("/find/use-class")
	public ResponseEntity<List<PersistUser>> findUseClass() {
		return new ResponseEntity<List<PersistUser>>(persistUserService.findUseClass(), CODE_200);
	}
	
	@GetMapping("/find/use-func")
	public ResponseEntity<List<PersistUser>> findUseFunc() {
		return new ResponseEntity<List<PersistUser>>(persistUserService.findUseFunc(), CODE_200);
	}
	
	@GetMapping("/find/use-builder")
	public ResponseEntity<List<PersistUser>> findUseBuilder() {
		return new ResponseEntity<List<PersistUser>>(persistUserService.findUseBuilder(), CODE_200);
	}
	
	
}
