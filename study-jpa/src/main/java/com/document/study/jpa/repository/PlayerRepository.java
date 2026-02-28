package com.document.study.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.document.study.jpa.entity.Player;
import com.document.study.jpa.immutable.PlayerReadOnly;

public interface PlayerRepository extends JpaRepository<Player, String>, PlayerRepositoryCustom {

	
	@Query(nativeQuery = true, value = """
			SELECT p.code 
			     , p.age 
			     , p.name 
			     , c.card_no 
			     , c.brand_nm
			     , JSON_ARRAYAGG(s.code) AS positions
			  FROM jpabegin.player p
			  LEFT JOIN jpabegin.player_card c
			    ON p.code = c.player_code
			  LEFT JOIN jpabegin.player_position s
			    ON p.code = s.player_code 
			 GROUP BY p.code 
			""")
	List<PlayerReadOnly> findQuery();

	
	@Query(nativeQuery = true, value = """
			SELECT p.code 
			     , p.age 
			     , p.name 
			     , c.card_no 
			     , c.brand_nm
			     , JSON_ARRAYAGG(s.code) AS positions
			  FROM jpabegin.player p
			  LEFT JOIN jpabegin.player_card c
			    ON p.code = c.player_code
			  LEFT JOIN jpabegin.player_position s
			    ON p.code = s.player_code
			 WHERE p.code = :player_code
			 GROUP BY p.code 
			""")
	PlayerReadOnly findQueryOne(@Param("player_code") String code);
	
}
