package com.document.study.jpa.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.document.study.jpa.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
