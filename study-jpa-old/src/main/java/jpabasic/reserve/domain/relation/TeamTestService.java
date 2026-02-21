package jpabasic.reserve.domain.relation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jpabasic.reserve.domain.TestService;
import jpabasic.reserve.domain.relation.entity.Team;
import jpabasic.reserve.domain.relation.entity.TeamInfo;

public class TeamTestService extends TestService {
	
	public static void main(String[] args) {
		
		TeamTestService service = new TeamTestService();
		
		test(PERSIST, service);
//		test(FIND   , service);
//		test(CHANGE, service);
//		test(FIND   , service);
//		test(REMOVE , service);
    }
	
	@Override
	public void persist(EntityManager manager, EntityTransaction transaction) {

		Team team4 = new Team("T04", "우주");
		Team team5 = new Team("T05", "심해");
		
		TeamInfo info = team4.getInfo();
		info.initValue("허지구", "01055558888", "주소");
		
		manager.persist(team4);
		manager.persist(team5);
		
		// new Team("T04", "우주") 일 때
		// TeamInfo 강제 생성.
		// @OneToOne(optional = false) 를 만들고 싶었음.
	};
	
	@Override
	public void find(EntityManager manager, EntityTransaction transaction) {
		
		Team team4 = manager.find(Team.class, "T04");
		
		System.out.println("1. " + team4);
		System.out.println("2. " + team4.getInfo());
		
		// [ FetchType.EAGER ] 는 find 일 때 같이 조회(영속화)하고, [ optional=false ] 라서 inner join.
		// 1. {"code":"T04","name":"우주"}
		// 2. {"address":"주소","ownerName":"허지구","mobile":"01055558888","teamCode":"T04"}
	};
	
	@Override
	public void change(EntityManager manager, EntityTransaction transaction) {
		
		Team team4 = manager.find(Team.class, "T04");
		team4.changeName("은하");
		
		TeamInfo info = team4.getInfo();
		info.changeOwnerName("전지구");
		info.changeMobile("01044447777");
		
		// cascade = CascadeType.ALL 관계없이.
		// find 되면 엔티티를 영속화 시키고 자식도 영속화 시킴.
		// 변경하게 되면 더티채킹으로 다 같이 변경된다.
		// 1. {"code":"T04","name":"은하"}
		// 2. {"address":"주소","ownerName":"전지구","mobile":"01044447777","teamCode":"T04"}
	}
	
	@Override
	public void remove(EntityManager manager, EntityTransaction transaction) {
		
		Team team4 = manager.find(Team.class, "T04");
		Team team5 = manager.find(Team.class, "T05");
		
		manager.remove(team4);
		manager.remove(team5);
		
		// delete from team_info where team_code=?
		// delete from Team where code=?
		// delete from team_info where team_code=?
		// delete from Team where code=?
	}
}
