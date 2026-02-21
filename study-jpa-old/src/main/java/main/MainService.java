package main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class MainService {

	
	public static EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpabegin");;
	
	public static EntityManager manager = factory.createEntityManager();
	
	public static EntityTransaction transaction = manager.getTransaction();
	
	public static void main(String[] args) {
        
        try {
        	System.out.println("===========================");
        	System.out.println("");
        	
        	MainService.init();
        	
        	System.out.println("");
        	System.out.println("===========================");
        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
            manager.close();
        }
        
        factory.close();
    }
	
	
	public static void init() {
		
	}
	
}
