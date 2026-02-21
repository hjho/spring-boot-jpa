package com.document.study.jpa.test.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.document.study.jpa.entity.TestTeam;
import com.document.study.jpa.test.service.TestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

	private final TestService testService;
	
	
	@GetMapping("/find")
	public List<TestTeam> findAll() {
		return testService.findAll();
	}
	
	@GetMapping("/find/{code}")
	public Optional<TestTeam> find(@PathVariable String code) {
		return testService.find(code);
	}
}
