package jpabasic.reserve.domain.relation;

import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jpabasic.reserve.domain.TestService;
import jpabasic.reserve.domain.relation.entity.AssignPlayer;
import jpabasic.reserve.domain.relation.entity.AssignPlayerKey;
import jpabasic.reserve.domain.relation.entity.Player;
import jpabasic.reserve.domain.relation.entity.PlayerPosition;
import jpabasic.reserve.domain.relation.entity.Team;
import jpabasic.reserve.domain.relation.value.Position;

public class AssignPlayerTestService extends TestService {
	
	public static void main(String[] args) {
		
		AssignPlayerTestService service = new AssignPlayerTestService();
		
		if(args.length > 0) {
			for (String name : args) {
				System.out.println("\n AssignPlayerTestService Run Name: " + name);
				test(name, service);
			}
			
			return;
		}
		
		test(CREATE, service);
		
		test(PERSIST, service);
		test(FIND   , service);
		
		test(CHANGE, service);
		test("find_one", service);
		
		// test("change_pk", service);
		// test("remove_team", service);
		
		test(REMOVE , service);
    }
	
	@Override
	public void my_function(String name, EntityManager manager, EntityTransaction transaction) {
		switch (name) {
			case "find_one": this.find_one(manager, transaction); break;
			case "change_pk": this.change_pk(manager, transaction); break;
			case "remove_team": this.remove_team(manager, transaction); break;
			default: break;
		}
	}
	
	@Override
	public void create(EntityManager manager, EntityTransaction transaction) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		String now = sdf.format(new Date());
		
		// Team 설정.
		Team team1 = new Team("T01", "땅");
		Team team2 = new Team("T02", "바다");
		Team team3 = new Team("T03", "하늘");
		Team team4 = new Team("T04", "우주");
		Team team5 = new Team("T05", "심해");
		
		team1.initTeamInfo("허육군", "01011112222", "영토");
		team2.initTeamInfo("허해군", "01033334444", "영해");
		team3.initTeamInfo("허공군", "01055556666", "영공");
		team4.initTeamInfo("어벤져스", "01077778888", "우리은하");
		
		manager.persist(team1);
		manager.persist(team2);
		manager.persist(team3);
		manager.persist(team4);
		manager.persist(team5);
		
		// Player 설정.
		Player player1 = new Player("P01", "호랑이", 33L, now, now);
		Player player2 = new Player("P02", "사자", 55L, now, now);
		Player player3 = new Player("P03", "고래", 21L, now, now);
		Player player4 = new Player("P04", "상어", 48L, now, now);
		Player player5 = new Player("P05", "독수리", 50L, now, now);
		Player player6 = new Player("P06", "토끼", 8L, now, now);
		
		player1.setCard("1357", "신한카드");
		player4.setCard("2468", "국민카드");
		player5.setCard("4567", "기업카드");
		player6.setCard("9876", "썬카드");
		
		manager.persist(player1);
		manager.persist(player2);
		manager.persist(player3);
		manager.persist(player4);
		manager.persist(player5);
		manager.persist(player6);
		
		PlayerPosition pf1 = player1.initPosition(Position.F, 1L);
		PlayerPosition pd2 = player2.initPosition(Position.D, 10L);
		PlayerPosition pf2 = player2.initPosition(Position.F, 8L);
		PlayerPosition pd5 = player5.initPosition(Position.D, 1L);
		PlayerPosition pu5 = player5.initPosition(Position.U, 23L);
		PlayerPosition pf6 = player6.initPosition(Position.F, 5L);
		PlayerPosition pd6 = player6.initPosition(Position.D, 1L);
		
		manager.persist(pf1);
		manager.persist(pd2);
		manager.persist(pf2);
		manager.persist(pd5);
		manager.persist(pu5);
		manager.persist(pf6);
		manager.persist(pd6);
		
		// Team1: 정상.
		// Team2: 정상. 
		// Team3: 정상.
		// Team4: 정상.
		// Team5: 팀정보 없음.
		
		// Player1: 정상.
		// Player2: 카드정보 없음. 
		// Player3: 카드정보 없음. 포지션정보 없음.
		// Player4: 포지션정보 없음.
		// Player5: 정상.
		// Player6: 정상.
	};
	
	@Override
	public void persist(EntityManager manager, EntityTransaction transaction) {
		
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
		
		// Team4: 계약선수 없음.
		// Team5: 계약선수 없음.
		
		// Player5: 계약팀 두 곳.
		// Player6: 계약팀 없음.
	}
	
	@Override
	public void find(EntityManager manager, EntityTransaction transaction) {
		AssignPlayer assign1 = manager.find(AssignPlayer.class, new AssignPlayerKey("T01", "P01"));
		AssignPlayer assign2 = manager.find(AssignPlayer.class, new AssignPlayerKey("T01", "P02"));
		AssignPlayer assign3 = manager.find(AssignPlayer.class, new AssignPlayerKey("T02", "P03"));
		AssignPlayer assign4 = manager.find(AssignPlayer.class, new AssignPlayerKey("T02", "P04"));
		AssignPlayer assign5 = manager.find(AssignPlayer.class, new AssignPlayerKey("T01", "P05"));
		AssignPlayer assign6 = manager.find(AssignPlayer.class, new AssignPlayerKey("T03", "P05"));
		
		System.out.println("\n");
		System.out.println("1. " + assign1);
		System.out.println("2. " + assign2);
		System.out.println("3. " + assign3);
		System.out.println("4. " + assign4);
		System.out.println("5. " + assign5);
		System.out.println("6. " + assign6);
		System.out.println("\n");
		
		
		// 1. {"team":{"code":"T01","name":"땅"},"player":{"code":"P01","name":"호랑이","age":33,"created":"2026.02.16 15:31:53"},"backNo":17,"nickName":"관악산 호랑이"}
		// 2. {"team":{"code":"T01","name":"땅"},"player":{"code":"P02","name":"사자","age":55,"created":"2026.02.16 15:31:53"},"backNo":10,"nickName":"에버랜드 사자"}
		// 3. {"team":{"code":"T02","name":"바다"},"player":{"code":"P03","name":"고래","age":21,"created":"2026.02.16 15:31:53"},"backNo":3,"nickName":"돌고래"}
		// 4. {"team":{"code":"T02","name":"바다"},"player":{"code":"P04","name":"상어","age":48,"created":"2026.02.16 15:31:53"},"backNo":7,"nickName":"아기상어"}
		// 5. {"team":{"code":"T01","name":"땅"},"player":{"code":"P05","name":"독수리","age":50,"created":"2026.02.16 15:31:53"},"backNo":99}
		// 6. {"team":{"code":"T03","name":"하늘"},"player":{"code":"P05","name":"독수리","age":50,"created":"2026.02.16 15:31:53"},"nickName":"대머리 독수리"}

		/* AssignPlayer find query 
		    select
		        p1_0.code, p1_0.name, p1_0.age, p1_0.created, p1_0.updated,
		        c1_0.player_code, c1_0.card_no, c1_0.brand_nm,
		        t1_0.code, t1_0.name,
		        i1_0.team_code, i1_0.owner_name, i1_0.mobile, i1_0.address,
		        a1_0.back_no, a1_0.nick_name
		    from
		        assign_player a1_0 
		    join
		        Player p1_0 
		            on p1_0.code=a1_0.player_code 
		    left join
		        player_card c1_0 
		            on p1_0.code=c1_0.player_code 
		    join
		        Team t1_0 
		            on t1_0.code=a1_0.team_code 
		    join
		        team_info i1_0 
		            on t1_0.code=i1_0.team_code 
		    where
		        (
		            a1_0.player_code,a1_0.team_code
		        ) in((?,?))
		*/
	};
	
	@Override
	public void change(EntityManager manager, EntityTransaction transaction) {
		AssignPlayer assign5 = manager.find(AssignPlayer.class, new AssignPlayerKey("T01", "P05"));
		
		assign5.changeBackNo(1L);
		assign5.changeNickName("흰머리수리");
	}
	
	@Override
	public void remove(EntityManager manager, EntityTransaction transaction) {
		
		AssignPlayer assign1 = manager.find(AssignPlayer.class, new AssignPlayerKey("T01", "P01"));
		AssignPlayer assign2 = manager.find(AssignPlayer.class, new AssignPlayerKey("T01", "P02"));
		AssignPlayer assign3 = manager.find(AssignPlayer.class, new AssignPlayerKey("T02", "P03"));
		AssignPlayer assign4 = manager.find(AssignPlayer.class, new AssignPlayerKey("T02", "P04"));
		AssignPlayer assign5 = manager.find(AssignPlayer.class, new AssignPlayerKey("T01", "P05"));
		AssignPlayer assign6 = manager.find(AssignPlayer.class, new AssignPlayerKey("T03", "P05"));
		
		manager.remove(assign1);
		manager.remove(assign2);
		manager.remove(assign3);
		manager.remove(assign4);
		manager.remove(assign5);
		manager.remove(assign6);
	}
	
	public void find_one(EntityManager manager, EntityTransaction transaction) {
		AssignPlayer assign5 = manager.find(AssignPlayer.class, new AssignPlayerKey("T01", "P05"));
		System.out.println("5. " + assign5);
		// 5. {"team":{"code":"T01","name":"땅"},"player":{"code":"P05","name":"독수리","age":50,"created":"2026.02.16 15:31:53"},"backNo":1,"nickName":"흰머리수리"}
	}
	
	@SuppressWarnings("deprecation")
	public void change_pk(EntityManager manager, EntityTransaction transaction) {
		
		Team team2 = manager.find(Team.class, "T02");
		
		AssignPlayer assign5 = manager.find(AssignPlayer.class, new AssignPlayerKey("T01", "P05"));
		
		assign5.changeTeam(team2);
		
		// Caused by: jakarta.persistence.PersistenceException: Converting `org.hibernate.HibernateException` to JPA `PersistenceException`
		// Caused by: org.hibernate.HibernateException:  identifier of an instance of [AssignPlayer] was altered from [AssignPlayerKey@281f80] to [AssignPlayerKey@281f61]
		// JPA/Hibernate 영속성 컨텍스트(Persistence Context)에서 관리되는 엔티티의 식별자(PK) 값을 변경하려고 할 때 발생하는 전형적인 문제.
		// 식별자 수정 금지 및 삭제 후 재생성.
	}
	
	public void remove_team(EntityManager manager, EntityTransaction transaction) {
		Team team2 = manager.find(Team.class, "T02");
		
		manager.remove(team2);
		
		// Cannot delete or update a parent row: a foreign key constraint fails
		//  (`jpabegin`.`assign_player`, CONSTRAINT `FKdd3aeg98xdbilsovc8ybnkndo` FOREIGN KEY (`team_code`) REFERENCES `team` (`code`))
		// 상위 행을 삭제하거나 업데이트할 수 없습니다: 외부 키 제약 조건이 실패합니다
	}
	
	
}
