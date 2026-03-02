package com.document.study.jpa.business.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.document.study.jpa.business.service.ManagerPagingService;
import com.document.study.jpa.entity.Manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/manager-paging")
@Slf4j
@RequiredArgsConstructor
public class ManagerPagingController {
	
	final HttpStatusCode CODE_200 = HttpStatusCode.valueOf(HttpStatus.OK.value());
	final HttpStatusCode CODE_500 = HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
	
	
	private final ManagerPagingService managerPagingService;
	
	@GetMapping("/sort")
	public ResponseEntity<List<Manager>> sort() {
		return new ResponseEntity<List<Manager>>(managerPagingService.sort(), CODE_200);
	}
	
	@GetMapping("/page")
	public ResponseEntity<List<Manager>> page() {
		return new ResponseEntity<List<Manager>>(managerPagingService.page(), CODE_200);
	}
	
}