package jpabasic.reserve.domain;

import java.util.Map;
import java.util.Objects;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public abstract class TestService {
	
	public static final String PERSIST = "persist"; 
	public static final String FIND    = "find"; 
	public static final String CHANGE  = "change"; 
	public static final String REMOVE  = "remove"; 
	public static final String INIT    = "init"; 
	
	public void persist(EntityManager manager, EntityTransaction transaction) {}
	
	public void find(EntityManager manager, EntityTransaction transaction) {}
	
	public void change(EntityManager manager, EntityTransaction transaction) {}
	
	public void remove(EntityManager manager, EntityTransaction transaction) {}
	
	public void init(EntityManager manager, EntityTransaction transaction) {}
	
	
	public static void test(String name, TestService service) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpabegin");;
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		
		try {
			Map<String, Object> properties = factory.getProperties();
			String ddl = Objects.toString(properties.get("hibernate.hbm2ddl.auto"), "");
			
			if("create".equals(ddl)) {
				System.out.println("\nONLY INIT TEST");
				System.out.println(" - please change 'hibernate.hbm2ddl.auto' to 'update'\n");
				
				service.init(manager, transaction);
				
			} else {
				
				System.out.println("############### test_"+ name +" start");
				transaction.begin();
				System.out.println("\n\n");
				
				switch(name) {
					case PERSIST: service.persist(manager, transaction); break;
					case FIND   : service.find(manager, transaction); break;
					case CHANGE : service.change(manager, transaction); break;
					case REMOVE : service.remove(manager, transaction); break;
					case INIT   : 
						 default: service.init(manager, transaction); break;
				}
				
				System.out.println("\n\n");
				transaction.commit();
				System.out.println("############### test_"+ name +" end");
			}
			
        } catch (Exception ex) {
            ex.printStackTrace();
            
            System.out.println("\n\n\n ### ROLLBACK ###\n\n\n");
            transaction.rollback();
        } finally {
            manager.close();
        }
		
		factory.close();
	}

}
