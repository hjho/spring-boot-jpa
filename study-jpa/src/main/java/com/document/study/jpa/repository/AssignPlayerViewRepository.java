package com.document.study.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.document.study.jpa.entity.AssignPlayerView;
import com.document.study.jpa.entity.AssignPlayerViewKey;

public interface AssignPlayerViewRepository extends JpaRepository<AssignPlayerView, AssignPlayerViewKey> {
	
}
