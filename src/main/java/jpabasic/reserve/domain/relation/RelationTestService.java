package jpabasic.reserve.domain.relation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jpabasic.reserve.domain.TestService;
import jpabasic.reserve.domain.relation.entity.AssignPlayer;
import jpabasic.reserve.domain.relation.entity.AssignPlayerKey;
import jpabasic.reserve.domain.relation.entity.Player;
import jpabasic.reserve.domain.relation.entity.Team;

public class RelationTestService extends TestService {
	
	/**
	 * <pre>
	 * CascadeType 에 대해. {@link PlayerTestService}
	 *  - persist: 부모 엔티티를 저장(em.persist(parent))할 때, 자식 엔티티들도 자동으로 persist() 하는 기능.
	 *  - remove: 부모 엔티티를 저장(em.remove(parent))할 때, 자식 엔티티들도 자동으로 remove() 하는 기능.
	 *  - refresh: 부모 엔티티를 새로 고침할 때, 연관된 자식 엔티티들까지 함께 데이터베이스에서 새로고침(DB로부터 다시 조회)하는 옵션.
	 *  - detach: 영속성 컨텍스트에서 분리 시 자식도 분리하는 옵션. 
	 *  - merge : 준영속 상태의 부모를 다시 영속화할 때 자식도 함께 영속 상태로 병합하는 옵션.
	 *  
	 *  <pre>
	 */
	public static void main(String[] args) {
		RelationTestService service = new RelationTestService();
		
		test(CREATE, service);
		
//		test(REFRESH, service);
		
		test(FIND, service);
		
//		test(CHANGE, service);
		
//		test(REMOVE, service);
    }
	
	@Override
	public void my_function(String name, EntityManager manager, EntityTransaction transaction) {
		switch (name) {
			case "refresh": this.refresh(manager, transaction); break;
			default: break;
		}
	}
	
	@Override
	public void create(EntityManager manager, EntityTransaction transaction) {
		// TEAM, PLAYER 등록.
		AssignPlayerTestService.main(new String[] {CREATE});
		
		Team team1 = manager.find(Team.class, "T01");
		Team team2 = manager.find(Team.class, "T02");
		Team team3 = manager.find(Team.class, "T03");
		
		Player player1 = manager.find(Player.class, "P01");
		Player player2 = manager.find(Player.class, "P02");
		Player player3 = manager.find(Player.class, "P03");
		Player player4 = manager.find(Player.class, "P04");
		Player player5 = manager.find(Player.class, "P05");
		
		// Assign Player 설정.
		AssignPlayer assign1 = new AssignPlayer(team1, player1, 17L, "관악산 호랑이");
		AssignPlayer assign2 = new AssignPlayer(team1, player2, 10L, "에버랜드 사자");
		
		AssignPlayer assign3 = new AssignPlayer(team2, player3, 3L, "돌고래");
		AssignPlayer assign4 = new AssignPlayer(team2, player4, 7L, "아기상어");
		
		AssignPlayer assign5 = new AssignPlayer(team3, player5, null, "대머리 독수리");
		AssignPlayer assign6 = new AssignPlayer(team1, player5, 99L, null);
		
		manager.persist(assign1);
		manager.persist(assign2);
		manager.persist(assign3);
		manager.persist(assign4);
		manager.persist(assign5);
		manager.persist(assign6);
	}
	
	@Override
	public void find(EntityManager manager, EntityTransaction transaction) {
		
		Team team1 = manager.find(Team.class, "T01");
		System.out.println("Team: " + team1);
		for (AssignPlayer player : team1.getPlayers()) {
			System.out.println("Player: " + player);
		}
		
		// Team: {"code":"T01","name":"땅"}
		// SQL
		// 		  select * 
		// 			from assign_player p1_0 
		//			join Player p2_0 
		//				on p2_0.code=p1_0.player_code 
		// 			left join player_card c1_0 
        // 				on p2_0.code=c1_0.player_code 
		// 		   where p1_0.team_code=?
		// Player: {"team":{"code":"T01","name":"땅"},"player":{"code":"P01","name":"호랑이","age":33,"created":"2026.02.16 17:21:03"},"backNo":17,"nickName":"관악산 호랑이"}
		// Player: {"team":{"code":"T01","name":"땅"},"player":{"code":"P02","name":"사자","age":55,"created":"2026.02.16 17:21:03"},"backNo":10,"nickName":"에버랜드 사자"}
		// Player: {"team":{"code":"T01","name":"땅"},"player":{"code":"P05","name":"독수리","age":50,"created":"2026.02.16 17:21:03"},"backNo":99}
	}
	
	
	@Override
	public void refresh(EntityManager manager, EntityTransaction transaction) {
		Team team1 = manager.find(Team.class, "T01");
		
		// 값을 사용하므로 팀과 그 팀의 소속된 플레이어를 영속화.
		System.out.println("플레이어 수: " + team1.getPlayers().size() + "명");
		
		// 계약선수 중 'P05'(독수리) 조회.
		AssignPlayer player5 = null;
		for (AssignPlayer player : team1.getPlayers()) {
			String playerCode = player.getPlayer().getCode();
			if("P05".equals(playerCode)) {
				System.out.println("독수리: " + player);
				// 독수리: {"team":{"code":"T01","name":"땅"},"player":{"code":"P05","name":"독수리","age":50,"created":"2026.02.16 17:21:03"},"backNo":99}
				player5 = player;
			}
		}
		
		// 독수리 선수 닉네임 변경. null -> "흰머리수리" 
		player5.changeNickName("흰머리수리");
		System.out.println("흰머리수리: " + player5);
		// 흰머리수리: {"team":{"code":"T01","name":"땅"},"player":{"code":"P05","name":"독수리","age":50,"created":"2026.02.16 17:21:03"},"backNo":99,"nickName":"흰머리수리"}
		
		// team1 refresh
		manager.refresh(team1);
		
		// "T01" Team 재조회. (team_info JOIN team LEFT JOIN assign_player)
		// refresh.
		// "T01", "P01" AssignPlayer 재조회. (assign_player JOIN player LEFT JOIN player_card JOIN team JOIN team_info)
		// "T01", "P02" AssignPlayer 재조회. (        WHERE player_code, team_code
		// "T01", "P05" AssignPlayer 재조회. (           IN (P05, T01)
		// "T01" Team 재조회. (team JOIN team_info LEFT JOIN assign_player LEFT JOIN player)
		// "P01" Player 재조회. (player_card JOIN player)
		// "P02" Player 재조회. (player_card JOIN player)
		// "P03" Player 재조회. (player_card JOIN player)
		
		// refresh 가 없으면.
		// 팀1의 독수리 선수 닉네임은 null 에서 "흰머리수리" 로 변경됨.
		// refresh: 부모 엔티티를 새로 고침할 때, 연관된 자식 엔티티들까지 함께 데이터베이스에서 새로고침(DB로부터 다시 조회)하는 옵션.
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public void change(EntityManager manager, EntityTransaction transaction) {
		
		Team team1 = manager.find(Team.class, "T01");
		
		AssignPlayer assign5 = manager.find(AssignPlayer.class, new AssignPlayerKey("T02", "P03"));
		
		assign5.changeTeam(team1);
		
		// Caused by: jakarta.persistence.PersistenceException: Converting `org.hibernate.HibernateException` to JPA `PersistenceException`
		// Caused by: org.hibernate.HibernateException:  identifier of an instance of [AssignPlayer] was altered from [AssignPlayerKey@281f80] to [AssignPlayerKey@281f61]
		// JPA/Hibernate 영속성 컨텍스트(Persistence Context)에서 관리되는 엔티티의 식별자(PK) 값을 변경하려고 할 때 발생하는 전형적인 문제.
		// 식별자 수정 금지 및 삭제 후 재생성.
	}
	
	@Override
	public void remove(EntityManager manager, EntityTransaction transaction) {
		Team team1 = manager.find(Team.class, "T01");
		
		manager.remove(team1);
		
		// Cannot delete or update a parent row: a foreign key constraint fails
		//  (`jpabegin`.`assign_player`, CONSTRAINT `FKdd3aeg98xdbilsovc8ybnkndo` FOREIGN KEY (`team_code`) REFERENCES `team` (`code`))
		// 상위 행을 삭제하거나 업데이트할 수 없습니다: 외부 키 제약 조건이 실패합니다
	}
	
}
