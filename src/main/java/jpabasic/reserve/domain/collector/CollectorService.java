package jpabasic.reserve.domain.collector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpabasic.reserve.domain.collector.embed.Choice;
import jpabasic.reserve.domain.collector.embed.GrantedPermission;
import jpabasic.reserve.domain.collector.embed.PropValue;
import jpabasic.reserve.domain.collector.entity.Document;
import jpabasic.reserve.domain.collector.entity.Question;
import jpabasic.reserve.domain.collector.entity.Role;

public class CollectorService {

	
	public static EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpabegin");;
	
	public static EntityManager manager = factory.createEntityManager();
	
	public static EntityTransaction transaction = manager.getTransaction();
	

	public static void setTest() {
		
		String id = "1"; 
		
		transaction.begin();
		
		Role role = manager.find(Role.class, id);
		
		if(role == null) {
			System.out.println("NULL");
			
			Set<String> permissions = new HashSet<>(Arrays.asList("A", "B", "C", "A"));
			
			GrantedPermission perm1 = new GrantedPermission("permis_1", "grant_1");
			GrantedPermission perm2 = new GrantedPermission("permis_2", "grant_2");
			Set<GrantedPermission> grantedPermissions = new HashSet<>(Arrays.asList(perm1, perm2, perm1));
			
			role = new Role(id, "TEST", permissions, grantedPermissions);
			
			manager.persist(role);
			
		} else {
			System.out.println("NOT NULL");
			manager.remove(role);
//			role.revokeAll();
//			role.setPermissions(new HashSet<>());
		}
		
		transaction.commit();
		
		System.out.println("");
		System.out.println("SET ROLE, GRANTED_PERMISSION");
		System.out.println(role);
		
	}
	
	public static void listTest() {
		
		String id = "1"; 
		
		transaction.begin();
		
		Question question = manager.find(Question.class, id);
		
		if(question == null) {
			System.out.println("NULL");
			
			List<String> question1 = Arrays.asList("C", "A", "B");
			
			List<Choice> question2 = new ArrayList<>();
			question2.add(new Choice("test3", true));
			question2.add(new Choice("test1", false));
			question2.add(new Choice("test2", true));
			
			question = new Question(id, "TEST", question1, question2);
			
			manager.persist(question);
			
		} else {
			System.out.println("NOT NULL");
			manager.remove(question);
//			question.revokeAll();
//			question.setPermissions(new HashSet<>());
		}
		
		transaction.commit();
		
		System.out.println("");
		System.out.println("LIST QUESTION, CHOICE");
		System.out.println(question);
		
	}
	
	public static void mapTest() {
		
		String id = "1"; 
		
		transaction.begin();
		
		Document document = manager.find(Document.class, id);
		
		if(document == null) {
			System.out.println("NULL");
			
			Map<String, String> props1 = new HashMap<>();
			props1.put("ageYn", "Y");
			props1.put("headYn", "Y");
			
			Map<String, PropValue> props2 = new HashMap<>();
			props2.put("timeYn", new PropValue("N", false));
			props2.put("moneyYn", new PropValue("N", false));
			props2.put("healthYn", new PropValue("N", false));
			
			document = new Document(id, "test", "content", props1, props2);
			
			manager.persist(document);
			
		} else {
			System.out.println("NOT NULL");
			manager.remove(document);
			
		}
		
		transaction.commit();
		
		System.out.println("");
		System.out.println("MAP DOCUMENT, PROP_VALUE");
		System.out.println(document);
		
	}
	
	
	public static void main(String[] args) {
        
        try {
        	System.out.println("===========================");
        	System.out.println("");
        	System.out.println("");
        	
//        	CollectorService.setTest();
        	
//        	CollectorService.listTest();
        	
        	CollectorService.mapTest();
        	
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
