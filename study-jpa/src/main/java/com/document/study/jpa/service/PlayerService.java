package com.document.study.jpa.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.document.study.jpa.embed.Position;
import com.document.study.jpa.entity.Player;
import com.document.study.jpa.entity.PlayerPosition;
import com.document.study.jpa.repository.PlayerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlayerService {
	
	private final PlayerRepository playerRepository;
	
	
	@Transactional(readOnly = true)
	public List<Player> findAll() {
		List<Player> players = playerRepository.findAll();
		
		for (Player player : players) {
			log.debug("## Player: {}", player);
		}
		
		return players;
	}
	
	@Transactional(readOnly = true)
	public Player find(String code) {
		Optional<Player> players = playerRepository.findById(code);
		
		Player player = (players.isPresent()) ? players.get() : null;

		log.debug("## Player: {}", player);
		return player;
	}
	
	/**
	 * PK가 같은 엔티티를 save 하면 merge 됨. 에러를 일으키진 않음.
	 * 
	 * 예상결과: card 는 등록되고, position 은 등록 안되는 건데,,,
	 *  왜 반대ㅠ
	 *  
	 * JPA의 save()는 엔티티의 ID가 null 이면 persist, null 이 아니면 merge 를 호출.
	 *   그러나 ID의 생성 전략이 @GeneratedValue가 아니고, 로직에서 ID 값을 직접 설정하는 경우가 있다면, 
	 *   이는 JpaRepository에서 신규 엔티티로 인식되지 않을 수 있다. 
	 *   이 경우, JpaRepository.save()는 엔티티가 이미 존재한다고 판단하고 merge()를 호출함.
	 *   
	 * persist() 비교.
	 */
	@Transactional
	public Player save() {
		// select count(*) from player p1_0
		long count = playerRepository.count();
		
		Player player7 = new Player("P07", "등록자", count);
		
		// 등록안됨. CascadeType.PERSIST
		player7.setCard("1111000022225555", "신한나라사랑카드");
		
		PlayerPosition ps1 = player7.initPosition(Position.F, 5L);
		PlayerPosition ps2 = player7.initPosition(Position.D, 1L);
		
		// 등록됨. CascadeType.MERGE
		player7.setPosition(Arrays.asList(ps1, ps2));
		
		Player player = playerRepository.save(player7);
		
		log.debug("Player: {}", player);
		return player;
	}
	
	@Transactional
	public void persist() {
		// select count(*) from player p1_0
		long count = playerRepository.count();
		
		Player player7 = new Player("P07", "등록자", count);
		
		// 등록됨. CascadeType.PERSIST
		player7.setCard("1111000022225555", "신한나라사랑카드");
		
		PlayerPosition ps1 = player7.initPosition(Position.F, 5L);
		PlayerPosition ps2 = player7.initPosition(Position.D, 1L);
		
		// 등록안됨. CascadeType.MERGE
		player7.setPosition(Arrays.asList(ps1, ps2));
		
		playerRepository.persist(player7);
	}

	
	@Transactional
	public Player change() {
		// select count(*) from player p1_0 where p1_0.code=? [P07]
		boolean exist = playerRepository.existsById("P07");
		
		Player player7 = null;
		if(exist) {
			Optional<Player> player = playerRepository.findById("P07");
			player7 = player.get();
			
			player7.changeName("수정자");
			
			log.debug("Player: {}", player7);
		}
		
		return player7;
	}
	
}
