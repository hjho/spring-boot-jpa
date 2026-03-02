package com.document.study.jpa.business.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.document.study.jpa.business.service.ManagerService;
import com.document.study.jpa.entity.Manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/manager")
@Slf4j
@RequiredArgsConstructor
public class ManagerController {
	
	final HttpStatusCode CODE_200 = HttpStatusCode.valueOf(HttpStatus.OK.value());
	final HttpStatusCode CODE_500 = HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
	
	
	private final ManagerService managerService;
	
	@PostMapping("/persist")
	public ResponseEntity<Boolean> persist() {
		return new ResponseEntity<Boolean>(managerService.persist(), CODE_200);
	}
	@PatchMapping("/change")
	public ResponseEntity<Boolean> change() {
		return new ResponseEntity<Boolean>(managerService.change(), CODE_200);
	}
	@DeleteMapping("/delete")
	public ResponseEntity<Boolean> delete() {
		return new ResponseEntity<Boolean>(managerService.delete(), CODE_200);
	}
	
	
	@GetMapping("/find/all")
	public ResponseEntity<List<Manager>> findAll() {
		return new ResponseEntity<List<Manager>>(managerService.findAll(), CODE_200);
	}
	@GetMapping("/find/and")
	public ResponseEntity<List<Manager>> findAnd() {
		return new ResponseEntity<List<Manager>>(managerService.findAnd(), CODE_200);
	}
	@GetMapping("/find/like")
	public ResponseEntity<List<Manager>> findLike() {
		return new ResponseEntity<List<Manager>>(managerService.findLike(), CODE_200);
	}
	@GetMapping("/find/boolean")
	public ResponseEntity<List<Manager>> findBoolean() {
		return new ResponseEntity<List<Manager>>(managerService.findBoolean(), CODE_200);
	}
	@GetMapping("/find/between")
	public ResponseEntity<List<Manager>> findBetween() {
		return new ResponseEntity<List<Manager>>(managerService.findBetween(), CODE_200);
	}
	
}