package com.document.study.jpa.business.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.StringUtils;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.document.study.jpa.business.repository.ManagerRepository;
import com.document.study.jpa.embed.Gender;
import com.document.study.jpa.entity.Manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ManagerService {
	
	private final ManagerRepository managerRepository;
	
	@Transactional(readOnly = true)
	public @Nullable List<Manager> findAll() {
		// 단건 Optional 처리.
		Optional<Manager> optional = managerRepository.findById(1L);
		Manager manager = (optional.isPresent()) ? optional.get() : null; 
		
		// 다건 iterator 처리.
		Iterable<Manager> iterable = managerRepository.findAll();
		List<Manager> managers = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
		
		log.debug("## find one: {}", manager);
		log.debug("## find all: {}", managers.size());
		return managers;
	}
	
	@Transactional(readOnly = true)
	public @Nullable List<Manager> findAnd() {
		return managerRepository.findByGenderAndForeigner(Gender.M, false);
	}
	
	@Transactional(readOnly = true)
	public @Nullable List<Manager> findLike() {
		List<Manager> managers1 = managerRepository.findByNameLike("%im.%");
		
		List<Manager> managers2 = managerRepository.findByNameContaining("im.");
		List<Manager> managers3 = managerRepository.findByNameNotContaining("im.");
		
		List<Manager> managers4 = managerRepository.findByNameStartingWith("heo.");
		
		List<Manager> managers5 = managerRepository.findByEmailEndingWith(".co.kr");
		List<Manager> managers6 = managerRepository.findByEmailEndingWithIgnoreCase(".cO.Kr");
		
		log.debug("## managers1: {}", managers1.size());
		log.debug("## managers2: {}", managers2.size());
		log.debug("## managers3: {}", managers3.size());
		log.debug("## managers4: {}", managers4.size());
		log.debug("## managers5: {}", managers5.size());
		log.debug("## managers6: {}", managers6.size());
		return managers1;
	}
	
	@Transactional(readOnly = true)
	public @Nullable List<Manager> findBoolean() {
		List<Manager> managers1 = managerRepository.findByBirthdayIsNull();
		List<Manager> managers2 = managerRepository.findByForeignerFalse();
		
		log.debug("## managers1: {}", managers1.size());
		log.debug("## managers2: {}", managers2.size());
		return managers1;
	}
	
	@Transactional(readOnly = true)
	public @Nullable List<Manager> findBetween() {
		// 1989-09-18 포함.
		List<Manager> managers1 = managerRepository.findByBirthdayBetween(LocalDate.of(1980, 1, 1), LocalDate.of(1989, 9, 18));
		// 1989-09-18 미포함.
		List<Manager> managers2 = managerRepository.findByBirthdayBefore(LocalDate.of(1989, 9, 18));
		// 1989-09-18 미포함.
		List<Manager> managers3 = managerRepository.findByBirthdayAfter(LocalDate.of(1989, 9, 18));
		// 1989-09-18 포함.
		List<Manager> managers4 = managerRepository.findByBirthdayLessThanEqual(LocalDate.of(1989, 9, 18));
		List<Manager> managers5 = managerRepository.findByBirthdayGreaterThan(LocalDate.of(1989, 9, 18));
				
		log.debug("## managers1: {}", managers1.size());
		log.debug("## managers2: {}", managers2.size());
		log.debug("## managers3: {}", managers3.size());
		log.debug("## managers4: {}", managers4.size());
		log.debug("## managers5: {}", managers5.size());
		return managers1;
	}
	
	@Transactional
	public @Nullable Boolean change() {
		Manager manager = managerRepository.findByName("choi.01");
		if(manager != null) {
//			manager.changeEmail("choi.00@study.com");
			// @LastModifiedBy, @LastModifiedDate
			// 두 값도 자동으로 수정됨.
			manager.changeBirthday(LocalDate.of(1995, 4, 21));
		}
		log.debug("### manager: {}", manager);
		return true;
	}
	
	@Transactional
	public @Nullable boolean delete() {
		int count1 = managerRepository.deleteByNameStartingWith("heo");
		log.debug("### count1: {}", count1);
		
		// managerRepository.deleteAll();
		return true;
	}
	
	@Transactional
	public boolean persist() {
		
		long count = managerRepository.count();
		log.debug("## count: {}", count);
		
		if(count == 0) {
			for (int i = 1; i <= 12; i++) {
				String name  = "kim." + StringUtils.leftPad(Integer.toString(i), 2, "0");
				String email = name + ((i < 10) ? "@study.com" : "@study.co.kr");
				LocalDate birthday = LocalDate.of(1980 + i, i, i*2);
				Gender gender = (i%2 == 0) ? Gender.M : Gender.F;
				boolean foreigner = (i%2 == 0);
				
				// String name, String email, LocalDate birthday, Gender gender, boolean foreigner
				Manager manager = new Manager(name, email, birthday, gender, foreigner);
				
				managerRepository.save(manager);
			}
			
			for (int i = 7; i <= 14; i++) {
				String name  = "heo." + StringUtils.leftPad(Integer.toString(i), 2, "0");
				String email = name + ((i < 10) ? "@study.com" : "@study.co.kr");
				LocalDate birthday = LocalDate.of(1990+i, i-5, i*2);
				Gender gender = (i%2 == 0) ? Gender.M : Gender.F;
				boolean foreigner = false;
				
				// String name, String email, LocalDate birthday, Gender gender, boolean foreigner
				Manager manager = new Manager(name, email, birthday, gender, foreigner);
				
				managerRepository.save(manager);
			}
			
			for (int i = 7; i <= 14; i++) {
				String name  = "pack." + StringUtils.leftPad(Integer.toString(i), 2, "0");
				String email = name + ((i < 10) ? "@study.com" : "@study.co.kr");
				LocalDate birthday = LocalDate.of(1984+i, i-5, i*2);
				Gender gender = (i == 10) ? Gender.M : Gender.F;
				boolean foreigner = (i == 10);
				
				// String name, String email, LocalDate birthday, Gender gender, boolean foreigner
				Manager manager = new Manager(name, email, birthday, gender, foreigner);
				
				managerRepository.save(manager);
			}
			
			return true;
			
		} else {
			// String name, String email, LocalDate birthday, Gender gender, boolean foreigner
			Manager manager = new Manager("choi.00", null, null, null, false);
			managerRepository.save(manager);
		}
		return false;
	}

}
