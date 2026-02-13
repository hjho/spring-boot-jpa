package jpabasic.reserve.domain.relation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpabasic.reserve.domain.relation.entity.AssignPlayer;
import jpabasic.reserve.domain.relation.entity.AssignPlayerKey;
import jpabasic.reserve.domain.relation.entity.Player;
import jpabasic.reserve.domain.relation.entity.PlayerCard;
import jpabasic.reserve.domain.relation.entity.PlayerPosition;
import jpabasic.reserve.domain.relation.entity.PlayerPositionKey;
import jpabasic.reserve.domain.relation.entity.Team;
import jpabasic.reserve.domain.relation.value.Position;

public class RelationService {
	
	public static EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpabegin");;
	
	public static EntityManager manager = factory.createEntityManager();
	
	public static EntityTransaction transaction = manager.getTransaction();

	
	public static void findAssignPlayer() {
		transaction.begin();
		System.out.println();
		
		AssignPlayer player = manager.find(AssignPlayer.class, new AssignPlayerKey("T01", "P01"));
		
		System.out.println("findAssignPlayer");
		System.out.println(player);
		System.out.println();
		
		transaction.commit();
	}
	
	public static void findPlayer() {
		transaction.begin();
		System.out.println();
		
		String playerCode = "P01";
		
		Player player = manager.find(Player.class, playerCode);

		System.out.println("findPlayer");
		System.out.println(player);
		System.out.println();
		
		PlayerPosition position = manager.find(PlayerPosition.class, new PlayerPositionKey(playerCode, Position.F));
		System.out.println("findPosition");
		System.out.println(position);
		System.out.println();
		
		PlayerCard card = manager.find(PlayerCard.class, playerCode);
		System.out.println("findCard");
		System.out.println(card);
		System.out.println();
		
		transaction.commit();
	}
	
	public static void main(String[] args) {
        
        try {
        	System.out.println("===========================");
        	System.out.println("");
        	System.out.println("");
        	
        	RelationService.init_data_setting();
        	System.out.println("");
        	System.out.println("");
        	System.out.println("");
        	System.out.println("");
        	System.out.println("");
        	System.out.println("");
        	System.out.println("");
        	
        	RelationService.findAssignPlayer();
        	
        	RelationService.findPlayer();
        	
        	System.out.println("");
        	System.out.println("");
        	System.out.println("===========================");
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
            
            System.out.println("");
            System.out.println("");
            System.out.println("ROLLBACK");
            System.out.println("");
            System.out.println("");
        } finally {
            manager.close();
        }
        
        factory.close();
    }
	
	
	public static void init_data_setting() {
		System.out.println("START   init_data_setting");
		
		transaction.begin();
		
		Team team1 = new Team("T01", "땅");
		Team team2 = new Team("T02", "바다");
		Team team3 = new Team("T03", "하늘");
		
		manager.persist(team1);
		manager.persist(team2);
		manager.persist(team3);
		
		Player player1 = new Player("P01", "호랑이");
		Player player2 = new Player("P02", "사자");
		Player player3 = new Player("P03", "고래");
		Player player4 = new Player("P04", "상어");
		Player player5 = new Player("P05", "독수리");
		
		manager.persist(player1);
		manager.persist(player2);
		manager.persist(player3);
		manager.persist(player4);
		manager.persist(player5);
		
		PlayerPosition pf1 = new PlayerPosition(player1, Position.F, 1L);
		PlayerPosition pd2 = new PlayerPosition(player2, Position.D, 10L); 
		PlayerPosition pf2 = new PlayerPosition(player2, Position.F, 8L); 
		PlayerPosition pd5 = new PlayerPosition(player5, Position.D, 1L); 
		PlayerPosition pu5 = new PlayerPosition(player5, Position.U, 23L); 
		
		manager.persist(pf1);
		manager.persist(pd2);
		manager.persist(pf2);
		manager.persist(pd5);
		manager.persist(pu5);
		
		System.out.println("########################");
		System.out.println(pf1);
		System.out.println(pf1.getCode().getKorName());
		System.out.println(pf1.getCode().getEngName());
		System.out.println(pf1.getCareer());
		System.out.println("########################");
		
		PlayerCard card1 = new PlayerCard(player1, "1234", "하나카드");
		PlayerCard card2 = new PlayerCard(player4, "2468", "신한카드");
		PlayerCard card3 = new PlayerCard(player5, "4567", "바로카드");
		
		manager.persist(card1);
		manager.persist(card2);
		manager.persist(card3);
		
		AssignPlayer assign1 = new AssignPlayer(team1, player1, 17L, "관악산 호랑이");
		AssignPlayer assign2 = new AssignPlayer(team1, player2, 10L, "에버랜드 사자");
		AssignPlayer assign3 = new AssignPlayer(team2, player3, 3L, "돌고래");
		AssignPlayer assign4 = new AssignPlayer(team2, player4, 7L, "아기상어");
		AssignPlayer assign5 = new AssignPlayer(team3, player5, 99L, null);
		AssignPlayer assign6 = new AssignPlayer(team1, player5, null, "대머리 독수리");
		
		manager.persist(assign1);
		manager.persist(assign2);
		manager.persist(assign3);
		manager.persist(assign4);
		manager.persist(assign5);
		manager.persist(assign6);
		
		transaction.commit();
		System.out.println("END     init_data_setting");
	}

}
