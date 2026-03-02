package com.document.study.jpa.business.service;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.document.study.jpa.business.repository.ManagerPagingRepository;
import com.document.study.jpa.embed.Gender;
import com.document.study.jpa.entity.Manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
	Repository<T, ID>
	  - 최상위 인터페이스로스프링 데이터가 엔티티를 관리하도록 마킹하는 역할만 수행합니다.
	  
	ListCrudRepository<T, ID>
	  - CrudRepository 를 상속받아 기본적인 CRUD(Create, Read, Update, Delete) 메서드를 제공합니다.
	  - findAll() 을 Iterable 가 아니라 List 로 받을 수 있음. 
	  
	ListPagingAndSortingRepository<T, ID>
	  - PagingAndSortingRepository 를 상속받아 페이징(Pageable) 및 정렬(Sort) 기능을 추가로 제공합니다.
	  - findAll() 을 Iterable 가 아니라 List 로 받을 수 있음. 
	
	JpaRepository<T, ID>
	  - ListCrudRepository 와 ListPagingAndSortingRepository 를 상속받은 가장 강력한 인터페이스입니다.
	  - QueryByExampleExecutor 를 상속받아서: Example 객체(검색 조건)를 사용할 수 있음.
	  - findAll() 을 Iterable 가 아니라 List 로 받을 수 있음. 
	  - JPA 특화 기능(배치 삭제, flush 등)을 포함하여 실무에서 가장 많이 사용됩니다.
*/
@Service
@Slf4j
@RequiredArgsConstructor
public class ManagerPagingService {
	
	private final ManagerPagingRepository managerPagingRepository;
	
	/**
	 * managers1: 기본 명명 규칙을 이용한 sort
	 * managers2: Sort 와 Example 을 이용한 sort
	 */
	@Transactional(readOnly = true)
	public @Nullable List<Manager> sort() {
		
		// managers1
		List<Manager> managers1 = managerPagingRepository.findByGenderOrderByBirthdayDesc(Gender.M);
		for (Manager manager : managers1) {
			log.debug("## manager1: {} {} {}", manager.getGender(), manager.getName(), manager.getBirthday());
		}
		
		// managers2
		// Sort.by(Sort.Order.desc("birthday"), Sort.Order.asc("name"));
		Sort sorts = Sort.by(Sort.Order.desc("birthday"));
		
		Example<Manager> example = Example.of(new Manager(Gender.M));
		
		List<Manager> managers2 = managerPagingRepository.findAll(example, sorts);
		for (Manager manager : managers2) {
			log.debug("## manager2: {} {} {}", manager.getGender(), manager.getName(), manager.getBirthday());
		}
		/* managers1 과 managers2 는 결과가 동일 함.
			## manager1: M heo.14 2004-09-28
			## manager1: M heo.12 2002-07-24
			## manager1: M heo.10 2000-05-20
			## manager1: M heo.08 1998-03-16
			## manager1: M pack.10 1994-05-20
			## manager1: M kim.12 1992-12-24
			## manager1: M kim.10 1990-10-20
			## manager1: M kim.08 1988-08-16
			## manager1: M kim.06 1986-06-12
			## manager1: M kim.04 1984-04-08
			## manager1: M kim.02 1982-02-04	
		 */
		return managers1;
	}
	
	/**
	 * managers1: 기본 명명 규칙을 이용한 sort & page
	 * managers2: Pageable, Sort, Example 을 이용한 page
	 */
	@Transactional(readOnly = true)
	public @Nullable List<Manager> page() {
		
		// managers1
		List<Manager> managers1 = managerPagingRepository.findTop10ByGenderOrderByBirthdayDesc(Gender.M);
		for (Manager manager : managers1) {
			log.debug("## manager1: {} {} {}", manager.getGender(), manager.getName(), manager.getBirthday());
		}
		
		// managers2
		Sort sorts = Sort.by("birthday").descending();
		int pageNumber = 1;
		int pageSize = 10;
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sorts);
		 
		Example<Manager> example = Example.of(new Manager(Gender.M));
		
		Page<Manager> page2 = managerPagingRepository.findAll(example, pageable);
		List<Manager> managers2 = page2.getContent();
		for (Manager manager : managers2) {
			log.debug("## manager2: {} {} {}", manager.getGender(), manager.getName(), manager.getBirthday());
		}
		log.debug("## Page total_pages   : {}", page2.getTotalPages());
		log.debug("## Page total_elements: {}", page2.getTotalElements());
		log.debug("## Page page_number   : {}", page2.getPageable().getPageNumber());
		log.debug("## Page page_size     : {}", page2.getPageable().getPageSize());
		log.debug("## Page content_size  : {}", page2.getContent().size());
		log.debug("## Page is_first      : {}", page2.isFirst());
		log.debug("## Page is_last       : {}", page2.isLast());
		log.debug("## Page is_empty      : {}", page2.isEmpty());
		log.debug("## Page has_next      : {}", page2.hasNext());
		log.debug("## Page has_previous  : {}", page2.hasPrevious());
		
	/* managers1 은 Top10만 가능. managers2 는 Top10 이후도 가능.
		## manager1: M heo.14 2004-09-28
		## manager1: M heo.12 2002-07-24
		## manager1: M heo.10 2000-05-20
		## manager1: M heo.08 1998-03-16
		## manager1: M pack.10 1994-05-20
		## manager1: M kim.12 1992-12-24
		## manager1: M kim.10 1990-10-20
		## manager1: M kim.08 1988-08-16
		## manager1: M kim.06 1986-06-12
		## manager1: M kim.04 1984-04-08

		## manager2: M kim.02 1982-02-04
		## Page total_pages   : 2
		## Page total_elements: 11
		## Page page_number   : 1  // 0이 1페이지임.
		## Page page_size     : 10
		## Page content_size  : 1
		## Page is_first      : false
		## Page is_last       : true
		## Page is_empty      : false
		## Page has_next      : false
		## Page has_previous  : true
	 */
		return managers2;
	}
	
}
