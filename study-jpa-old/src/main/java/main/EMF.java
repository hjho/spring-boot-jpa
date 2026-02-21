package main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMF {
    private static EntityManagerFactory emf;

    public static void init() {
        emf = Persistence.createEntityManagerFactory("jpabegin");
    }
    
    /**
    EntityManager가 제공하는 메서드 이용.
        - 저장: persist()
        - 조회: find()
        - 삭제: remove()
        - // 변경: merge()
    */
    public static EntityManager createEntityManager() {
        return emf.createEntityManager();
    }

    public static void close() {
        emf.close();
    }
}