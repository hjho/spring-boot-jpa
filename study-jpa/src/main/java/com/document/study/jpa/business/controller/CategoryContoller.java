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

import com.document.study.jpa.business.repository.CategoryRepository;
import com.document.study.jpa.entity.Category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryContoller {
	
	final HttpStatusCode CODE_200 = HttpStatusCode.valueOf(HttpStatus.OK.value());
	
	final HttpStatusCode CODE_500 = HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()); 
	
	private final CategoryRepository categoryRepository;
	
	
	@GetMapping("/find-all")
	public ResponseEntity<List<Category>> findAll() {
		
		List<Category> categorys = categoryRepository.findAll();
		
		return new ResponseEntity<List<Category>>(categorys, CODE_200);
	}
	
	@GetMapping("/find/{code}")
	public ResponseEntity<Category> find(@PathVariable Long code) {
		
		Optional<Category> category = categoryRepository.findById(code);
		
		log.debug("## Category isPresent: {}", category.isPresent());
		
		if(category.isEmpty()) {
			return new ResponseEntity<Category>(CODE_500);
		}
		return new ResponseEntity<Category>(category.get(), CODE_200);
	}
	
}
