package com.document.study.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.document.study.jpa.entity.Team;

public interface TeamRepository extends JpaRepository<Team, String> {

}
