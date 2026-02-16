package jpabasic.reserve.domain.relation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jpabasic.reserve.domain.TestService;
import jpabasic.reserve.domain.relation.entity.AssignPlayer;
import jpabasic.reserve.domain.relation.entity.Player;
import jpabasic.reserve.domain.relation.entity.Team;

public class RelationTestService extends TestService {
	
	public static void main(String[] args) {
		RelationTestService service = new RelationTestService();
		
		test(CREATE, service);
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

}
