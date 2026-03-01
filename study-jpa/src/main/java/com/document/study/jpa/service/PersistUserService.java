package com.document.study.jpa.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.document.study.jpa.entity.PersistUser;
import com.document.study.jpa.repository.PersistUserRepository;
import com.document.study.jpa.specific.SpecBuilder;
import com.document.study.jpa.specific.UserNameSpecification;
import com.document.study.jpa.specific.UserSpecs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersistUserService {
	
	private final PersistUserRepository persistUserRepository;
	
	@Transactional
	public boolean persist() {
		long count = persistUserRepository.count();
		if(count == 0) {
			for (int i = 1 ; i <= 20 ; i++) {
	            PersistUser persistUser = new PersistUser("document" + i + "@study.com", "범생이" + i, LocalDateTime.now());
	            persistUserRepository.save(persistUser);
	        }
			
			return true;
		}
		
		return false;
	}
	
	
	@Transactional(readOnly = true)
	public List<PersistUser> findUseClass() {
		UserNameSpecification spec = new UserNameSpecification("생이");
		List<PersistUser> list = persistUserRepository.findAll(spec);
		
		log.debug("## PersistUser findUseClass size: {}", list.size());
		return list;
	}
	
	
	@Transactional(readOnly = true)
	public List<PersistUser> findUseFunc() {
		Specification<PersistUser> likeSpec = UserSpecs.nameLike("생이");
		Specification<PersistUser> afterSpec = UserSpecs.createdAfter(LocalDateTime.now().minusHours(1));
		Specification<PersistUser> allSpec = likeSpec.and(afterSpec);
		List<PersistUser> list = persistUserRepository.findAll(allSpec);
		
		log.debug("## PersistUser findUseFunc size: {}", list.size());
		return list;
	}
	
	
	@Transactional(readOnly = true)
	public List<PersistUser> findUseBuilder() {
		
		String text = "생이";
		LocalDateTime time = LocalDateTime.now().minusHours(1);
		boolean isYn = true;
		
		Specification<PersistUser> specs = 
				SpecBuilder.builder(PersistUser.class)
				.ifHasText(text // text가 있으면.
						, value -> UserSpecs.nameLike(value))
				.ifNotNull(time // time이 null이 아니면.
						, value -> UserSpecs.createdAfter(value))
				.ifTrue(isYn // isYn 이 ture 이면.
						, () -> UserSpecs.isNameTen())
				.build();
		
		List<PersistUser> list = persistUserRepository.findAll(specs);
		
		log.debug("## PersistUser findUseFunc size: {}", list.size());
		return list;
	}
	
}