package jpabasic.reserve.domain.relation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpabasic.reserve.domain.relation.entity.Player;
import jpabasic.reserve.domain.relation.entity.PlayerCard;
import jpabasic.reserve.domain.relation.entity.PlayerPosition;
import jpabasic.reserve.domain.relation.value.Position;

public class PlayerTestService {
	
	/**
	 * hibernate.hbm2ddl.auto = update 으로 설정해서 사용. 
	 */
	private static void test(String name, PlayerTestService service) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpabegin");;
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		
		try {
			System.out.println("############### test_"+ name +" start");
			transaction.begin();
			System.out.println("\n\n");
			
			switch(name) {
				case "persist": service.persist(manager, transaction); break;
				case "find"   : service.find(manager, transaction); break;
				case "change" : service.change(manager, transaction); break;
				case "remove" : service.remove(manager, transaction); break;
			}
			
			System.out.println("\n\n");
			transaction.commit();
			System.out.println("############### test_"+ name +" end");
        } catch (Exception ex) {
            ex.printStackTrace();
            
            System.out.println("\n\n\n ### ROLLBACK ###\n\n\n");
            transaction.rollback();
        } finally {
            manager.close();
        }
		
		factory.close();
	}
	
	public static void main(String[] args) {
		
		PlayerTestService service = new PlayerTestService();
		
		PlayerTestService.test("persist", service);
		
		PlayerTestService.test("find", service);
		
		PlayerTestService.test("change", service);
		
		PlayerTestService.test("find", service);
		
		PlayerTestService.test("remove", service);
    }
	
	
	public void persist(EntityManager manager, EntityTransaction transaction) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		String now = sdf.format(new Date());
		
		// 1. player 상태에 따름.
		Player player6 = new Player("P06", "토끼", 8L, now, now);
		manager.persist(player6);
		
		// 2. cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REMOVE}.
		player6.setCard("9876", "썬카드");
		
		// 3. cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE}.
		PlayerPosition ps1 = player6.initPosition(Position.F, 5L);
		PlayerPosition ps2 = player6.initPosition(Position.D, 1L);
		// player6.changePosition(Arrays.asList(ps1, ps2));
		// ㄴPERSIST 일 때에는 DB Insert 됨.
		
		manager.persist(ps1);
		manager.persist(ps2);
		
		// 1. player 가 persist(등록) 될 때
		// 2. 설정값에 따라서 card 도 같이 persist 됨.
		// 3. 설정값에 따라서 position 은 별도로 persist 하여 등록헤야 함.
	}
	
	
	public void find(EntityManager manager, EntityTransaction transaction) {
		
		Player player = manager.find(Player.class, "P06");
		
		System.out.println("");
		System.out.println("1. " + player);
		
		// @OneToOne(fetch = FetchType.EAGER, optional = true): default
		System.out.println("2. " + player.getCard());
		
		// @@OneToMany(fetch = FetchType.LAZY, optional = true): default
		System.out.println("3. " + player.getPosition());
		
		// SQL: player left join player_card
		// 1. {"code":"P06","created":"2026.02.14 14:29:13","name":"토끼","age":8}
		// 2. {"brandNm":"썬카드","playerCode":"P06","cardNo":"9876"}
		
		// SQL: player_position 조회.
		// 3. [{"career":1,"code":"D","playerCode":"P06"}, {"career":5,"code":"F","playerCode":"P06"}]
		
		// [ FetchType.EAGER ] 는 find 일 때 같이 조회(영속화)하고, [ optional=true ] 라서 left join.
		// [ FetchType.LAZY ] 는 값을 사용 할 때 따로 조회(영속화).
	}
	
	public void change(EntityManager manager, EntityTransaction transaction) {
			
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		String now = sdf.format(new Date());
		
		// player 영속화.
		Player player = manager.find(Player.class, "P06");
		// player.card 영속화.
		PlayerCard card = player.getCard();
		List<PlayerPosition> positions = player.getPosition();
		positions.forEach((position) -> {
			// player.position 영속화.
			System.out.println(position);
		});
		
		// player 를 준영속 상태로 변경하고
		// 설정에 따라 card 와 positions 도 준영속 상태로 변경.
		manager.detach(player);
		
		// 1. player 상태에 따름.
		player.changeName("달토끼", now);
		
		// 2. cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REMOVE}.
		card.changeCardNo("9999");
		card.changeBrandNm("태양카드");
		
		// 3. cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE}.
		positions.forEach((position) -> {
			position.changeCareer(Position.D, 2L);
		});
		
		manager.merge(player);
		// 1. {"code":"P06","created":"2026.02.14 17:13:15","name":"달토끼","updated":"2026.02.14 17:15:39","age":8}
		// 2. {"brandNm":"썬카드","playerCode":"P06","cardNo":"9876"}
		// 3. [{"career":2,"code":"D","playerCode":"P06"}, {"career":5,"code":"F","playerCode":"P06"}]
		// merge 시 (1)과 (3)만 수정된 걸 알 수 있음.
	}
	
	public void remove(EntityManager manager, EntityTransaction transaction) {
			
		Player player = manager.find(Player.class, "P06");
		
		// 설정 값에 따라서 player, card, position 셋 다 삭제.
		// card    . cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REMOVE}.
		// position. cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REMOVE}.
		manager.remove(player);
		// delete from player_card where player_code=?
		// delete from player_position where code=? and player_code=?
		// delete from player_position where code=? and player_code=?
		// delete from Player where code=?
	}

}
