package com.document.study.jpa.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.document.study.jpa.business.data.TeamRVO;
import com.document.study.jpa.entity.Team;

public interface TeamRepository extends JpaRepository<Team, String> {

	@Query(nativeQuery = true, value = """
			select t.code
			     , t.name
			     , ti.address
			     , ti.mobile
			     , ti.owner_name
			     , (select count(*) from jpabegin.assign_player ap where ap.team_code = t.code) as player_cnt
			  from jpabegin.team t
			  left join jpabegin.team_info ti 
			    on t.code = ti.team_code  
			""")
	List<TeamRVO> findQuery();
	
	
	@Query(nativeQuery = true, value = """
			select t.code
			     , t.name
			     , ti.address
			     , ti.mobile
			     , ti.owner_name
			     , (select count(*) from jpabegin.assign_player ap where ap.team_code = t.code) as player_cnt
			  from jpabegin.team t
			  left join jpabegin.team_info ti 
			    on t.code = ti.team_code
			 where t.code = :team_code
			""")
	TeamRVO findQueryOne(@Param("team_code") String code);
}
