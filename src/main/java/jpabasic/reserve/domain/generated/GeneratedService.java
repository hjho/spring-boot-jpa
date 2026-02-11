package jpabasic.reserve.domain.generated;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpabasic.reserve.domain.generated.entity.AccessLog;
import jpabasic.reserve.domain.generated.entity.ActivityLog;
import jpabasic.reserve.domain.generated.entity.Employee;
import jpabasic.reserve.domain.generated.entity.Hotel;
import jpabasic.reserve.domain.generated.entity.Writer;
import jpabasic.reserve.domain.generated.value.Address;
import jpabasic.reserve.domain.generated.value.Grade;
import jpabasic.reserve.domain.generated.value.Intro;

public class GeneratedService {

	
	public static EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpabegin");;
	
	public static EntityManager manager = factory.createEntityManager();
	
	public static EntityTransaction transaction = manager.getTransaction();
	

	
	public static void employeeTest() {
		
		String id = "20260211"; 
		
    	transaction.begin();
    	
    	Employee employee = manager.find(Employee.class, id);
    	
    	if(employee == null) {
    		System.out.println("NULL");
    		
    		Address address1 = new Address("home1", "home2", "homecode");
    		Address address2 = new Address("work1", "work2", "workcode"); 
    		employee = new Employee(id, address1, address2);
    		
    		manager.persist(employee);
    		
    	} else {
    		System.out.println("NOT NULL");
    		manager.remove(employee);
    	}
    	
    	transaction.commit();
    	
    	System.out.println("");
    	System.out.println("EMPLOYEE");
    	System.out.println(employee);
	}

	public static void writerTest() {
		
		String id = "1"; 
		
		transaction.begin();
		
		Writer writer = manager.find(Writer.class, id);
		
		if(writer == null) {
			System.out.println("NULL");
			
			Address address = new Address("address1", "address2", "zipcode"); 
			Intro intro = new Intro("type", "내용");
			writer = new Writer("홍길동", address, intro);
			
			manager.persist(writer);
			
		} else {
			System.out.println("NOT NULL");
			manager.remove(writer);
		}
		
		transaction.commit();
		
		System.out.println("");
		System.out.println("WRITER");
		System.out.println(writer);
	}
	
	public static void hotelTest() {
		
		String id = "H01"; 
		
		transaction.begin();
		
		Hotel hotel = manager.find(Hotel.class, id);
		
		if(hotel == null) {
			System.out.println("NULL");
			
			hotel = new Hotel(id, "금천호텔", 10, Grade.S1);
			
			manager.persist(hotel);
			
		} else {
			System.out.println("NOT NULL");
			manager.remove(hotel);
		}
		
		transaction.commit();
		
		System.out.println("");
		System.out.println("HOTEL");
		System.out.println(hotel);
	}
	
	public static void activityLogTest() {
		
		String id = "2"; 
		
		transaction.begin();
		
		ActivityLog activityLog = manager.find(ActivityLog.class, id);
		
		if(activityLog == null) {
			System.out.println("NULL");
			
			activityLog = new ActivityLog("홍길동", "test");
			
			manager.persist(activityLog);
			
		} else {
			System.out.println("NOT NULL");
			manager.remove(activityLog);
		}
		
		transaction.commit();
		
		System.out.println("");
		System.out.println("ACTIVITY LOG");
		System.out.println(activityLog);
	}
	
	public static void accessLogTest() {
		
		String id = "1"; 
		
		transaction.begin();
		
		AccessLog accessLog = manager.find(AccessLog.class, id);
		
		if(accessLog == null) {
			System.out.println("NULL");
			
			accessLog = new AccessLog("/access/log/test");
			
			manager.persist(accessLog);
			
		} else {
			System.out.println("NOT NULL");
			manager.remove(accessLog);
		}
		
		transaction.commit();
		
		System.out.println("");
		System.out.println("ACCESS LOG");
		System.out.println(accessLog);
	}
	
	
	public static void main(String[] args) {
        
        try {
        	System.out.println("===========================");
        	System.out.println("");
        	System.out.println("");
        	
        	GeneratedService.employeeTest();
        	
        	GeneratedService.writerTest();
        	
//        	GeneratedService.hotelTest();
        	
//        	GeneratedService.activityLogTest();
        	
//        	GeneratedService.accessLogTest();
        	
        	
        	System.out.println("");
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
	
	
}
