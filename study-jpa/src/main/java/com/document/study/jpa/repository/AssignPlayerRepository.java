package com.document.study.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.document.study.jpa.entity.AssignPlayer;
import com.document.study.jpa.entity.AssignPlayerKey;

public interface AssignPlayerRepository extends JpaRepository<AssignPlayer, AssignPlayerKey> {

}
