package main;

import jakarta.persistence.*;
import jpabasic.reserve.domain.User;

import java.time.LocalDateTime;

public class UserSaveMain {
	
	/** 영속 컨텍스트(Persistence Context)
    - 엔티티를 메모리에 보관.
    - 엔티티 객체를 영속 객체에서 보관하고 하고 있다가.
    - Commit 시점에 해당 객체가 변경이 되었는지 추적하여 DB 반영.
    - (응용)엔티티 객체 <> (영속)영속 객체 <> (DB)레코드
    - 변경 트랜잭션 범위 안에서 존재하도록!.
	 */
	
	public static void main(String[] args) {

        // 영속 단위 기준으로 초기화.
        // presistence-unit 기준.
        // 커넥션 풀, DB연동에 필요한 자원 생성.
        // 기동 시 딱 한번 만 생성.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabegin");

        // JPA에 있어서 핵심. 
        // DB연동처리
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            User user = new User("user@user.com", "테스터", LocalDateTime.now());
            // DB 저장.
            // 저장과 쿼리 실행 시점. 
            entityManager.persist(user);

            // 실제 INSERT는 commit 일 때 처리!
            transaction.commit();
        } catch (Exception ex) {
            // 이미 존재: EntityExistsException
            ex.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }

        // 팩토리 닫음, 사용한자원 반환.
        emf.close();
    }
}
