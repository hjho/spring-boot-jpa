package com.document.study.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.document.study.jpa.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, String>, PlayerRepositoryCustom {

}
