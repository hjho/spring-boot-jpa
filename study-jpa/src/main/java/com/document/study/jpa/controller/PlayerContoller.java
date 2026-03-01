package com.document.study.jpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.document.study.jpa.entity.Player;
import com.document.study.jpa.repository.PlayerRepository;
import com.document.study.jpa.service.PlayerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/player")
@RequiredArgsConstructor
public class PlayerContoller {
	
	final HttpStatusCode CODE_200 = HttpStatusCode.valueOf(HttpStatus.OK.value());
	
	final HttpStatusCode CODE_500 = HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()); 
	
	private final PlayerRepository playerRepository;
	
	private final PlayerService playerService;
	
	
	@GetMapping("/find-all")
	public ResponseEntity<List<Player>> findAll() {
		
		List<Player> players = playerService.findAll();
		
		return new ResponseEntity<List<Player>>(players, CODE_200);
	}
	
	/***
	 * Cannot lazily initialize collection of role 'com.document.study.jpa.entity.Player.position' with key 'P01' (no session)
	 *  이 에러는 지연 로딩(Lazy Loading)으로 설정된 연관 엔티티를 영속성 컨텍스트(DB 세션)가 종료된 후에 조회하려 할 때 발생
	 *  Player 엔티티를 가져온 시점에는 position 컬렉션이 프록시 객체로 채워져 있었지만
	 *  이후 position 에 접근할 때 이미 트랜잭션이 끝나 세션이 닫혀(no Session) 실제 데이터를 가져오지 못하는 상황
	 *  
	 *  @Transactional 을 이용하여 세션을 유지한 상태로 Player.position을 조회하여 Lazy 로딩 해결.
	 *    - propagation = Propagation.SUPPORTS 으로 트랜잭션이 없으면 같은 오류 발생.
	 *    
	 *  spring.jpa.open-in-view: true 일 경우. 상관 없음. (default)
	 */
	@GetMapping("/find/{code}")
	public ResponseEntity<Player> find(@PathVariable String code) {
		
		Optional<Player> player = playerRepository.findById(code);
		
		log.debug("## Player isPresent: {}", player.isPresent());
		
		if(player.isEmpty()) {
			return new ResponseEntity<Player>(CODE_500);
		}
		return new ResponseEntity<Player>(player.get(), CODE_200);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Player> save() {
		
		Player player = playerService.save();
		
		return new ResponseEntity<Player>(player, CODE_200);
	}
	
	@PostMapping("/persist")
	public ResponseEntity<Player> persist() {
		
		playerService.persist();
		
		Player player = playerService.find("P07");
		
		return new ResponseEntity<Player>(player, CODE_200);
	}
	
	/**
	 * @PutMapping (전체 수정):
	 *  요청한 데이터로 리소스 전체를 완전히 덮어씁니다.
	 *  전송되지 않은 필드는 null 또는 기본값으로 초기화될 위험이 있습니다.
	 *  멱등성 보장: 같은 데이터로 1번 호출하나 10번 호출하나 결과가 같습니다.
	 * @PatchMapping (부분 수정):
	 *  리소스의 일부분만 변경합니다.
	 *  보내지 않은 필드는 기존 값을 유지합니다.
	 *  대역폭 효율적: 필요한 데이터만 전송합니다. 
	 */
	@PatchMapping("/change")
	public ResponseEntity<Player> change() {
		
		Player player = playerService.change();
		
		log.debug("## Player: {}", player);
		return new ResponseEntity<Player>(player, CODE_200);
	}
	
	@DeleteMapping("/delete/{code}")
	public ResponseEntity<?> delete(@PathVariable String code) {
		
		playerRepository.deleteById(code);
		
		return new ResponseEntity<>(CODE_200);
	}

	
}
