package com.document.study.jpa.business.repository;

import java.util.List;

import com.document.study.jpa.business.data.AssignPlayerPDto;
import com.document.study.jpa.business.data.AssignPlayerRDto;
import com.document.study.jpa.business.data.AssignPlayerVO;
import com.document.study.jpa.entity.AssignPlayer;

public interface AssignPlayerRepositoryCustom {
	
	
	AssignPlayer findQueryDslOne(String team, String player);
	
	
	List<AssignPlayerVO> findQueryDslAll();
	
	List<AssignPlayerVO> findQueryDslJoin();
	
	List<AssignPlayerRDto> findQueryDslDynamic(AssignPlayerPDto input);
	
}
