package com.document.study.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.document.study.jpa.entity.TestTeam;

public interface TeamRepository extends JpaRepository<TestTeam, String> {

}
