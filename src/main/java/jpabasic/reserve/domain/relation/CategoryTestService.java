package jpabasic.reserve.domain.relation;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jpabasic.reserve.domain.TestService;
import jpabasic.reserve.domain.relation.entity.Category;

public class CategoryTestService extends TestService {
	
	final static Long[] parent_code = new Long[] {1L, 8L, 12L, 19L};
	
	public static void main(String[] args) {
		
		CategoryTestService service = new CategoryTestService();
		
		// test(PERSIST, service);
		
		test(FIND, service);
		
		test("persist_one", service);
		
		test(CHANGE, service);
		
		test("find_one", service);
		
		test("remove_one", service);
		
		test("find_one", service);
		
		// test(REMOVE, service);
    }
	
	@Override
	public void my_function(String name, EntityManager manager, EntityTransaction transaction) {
		switch (name) {
			case "find_one": this.find_one(manager, transaction); break;
			case "remove_one": this.remove_one(manager, transaction); break;
			case "persist_one": this.persist_one(manager, transaction); break;
			default: break;
		}
	}
	public void persist_one(EntityManager manager, EntityTransaction transaction) {
		Category position = manager.find(Category.class, parent_code[3]);
		
		position.addChild(new Category("persist_one", ""));
		
		print(0, position);
	}
	public void find_one(EntityManager manager, EntityTransaction transaction) {
		Category parent3 = manager.find(Category.class, parent_code[2]);
		
		System.out.println(parent3);
		this.print(0, parent3);
	}
	public void remove_one(EntityManager manager, EntityTransaction transaction) {
		Category parent3 = manager.find(Category.class, parent_code[2]);
		
		for (Category child1 : parent3.getChildren()) {
			
			
			if("Player".equals(child1.getValue())) {
				System.out.println("### Player ###");
				for (Category child2 : child1.getChildren()) {
					
					if("PlayerPosition".equals(child2.getValue())) {
						System.out.println("### PlayerPosition ###");
						
						List<Category> children3 = child2.getChildren();
						
						// 1. REMOVE가 되고, 더티체킹도 되는 방법.
						children3.removeIf(c -> "change".equals(c.getValue()));
						List<Category> newlist = new ArrayList<>(children3);
						
						System.out.println("### change ###: " + newlist);
						// ### change ###: [{"parent_code":19,"code":23,"value":"persist_one","description":""}]
						
						child2.clearAndAddChildren(newlist);
						// children.clear();
				    	// children.addAll(newlist);
						// children 의 @OneToMany orphanRemoval 속성이 true 이여야 함. 
						// 이 속성이 있어야 clear()를 호출했을 때 기존 자식 엔티티들이 Parent와 연결이 끊어지면서 DB에서 DELETE 쿼리가 실행됨.
						// this.children = newlist; 는 절대 금지.
						
						/* 2. REMOVE는 되지만 더티체킹은 안됨.
						boolean isRemove = children3.removeIf(c -> "change".equals(c.getValue()));
						
						System.out.println("### change ###: " + children3);
						// ### change ###: true
						 
						System.out.println("### change ###: " + isRemove);  
						// ### change ###: [{"parent_code":39,"code":41,"value":"persist_one","description":""}]
						*/
						
						/* 3. REMOVE는 되지만 더티체킹은 안됨.
						for (Category child3 : children3) {
							if("change".equals(child3.getValue())) {
								boolean isRemove = child2.removeChild(child3);
								
								System.out.println("### change ###: " + isRemove);
								// ### change ###: true
								
								System.out.println("### change ###: " + child2.getChildren());
								// ### change ###: [{"parent_code":19,"code":22,"value":"persist_one","description":""}]
								break;
							}
						}
						 */
						break;
					}
					
				}
				break;
			}
		}
	}
	
	@Override
	public void persist(EntityManager manager, EntityTransaction transaction) {
		Category parent1 = new Category("generated", "");
		Category parent2 = new Category("collector", "");
		Category parent3 = new Category("relation", "");
		
		parent1.addChild(new Category("Employee", ""));
		parent1.addChild(new Category("Hotel", ""));
		parent1.addChild(new Category("Review", ""));
		parent1.addChild(new Category("Writer", ""));
		parent1.addChild(new Category("Accesslog", ""));
		parent1.addChild(new Category("Activitylog", ""));
		
		parent2.addChild(new Category("Role", ""));
		parent2.addChild(new Category("Question", ""));
		parent2.addChild(new Category("Document", ""));
		
		Category team   = new Category("Team", "");
		Category player = new Category("Player", "");
		
		Category team_1 = new Category("AssignPlayer", "");
		Category team_2 = new Category("TeamInfo", "");
		
		Category player_1 = new Category("AssignPlayer", "");
		Category player_2 = new Category("PlayerCard", "");
		Category player_3 = new Category("PlayerPosition", "");
		
		team.addChild(team_1);
		team.addChild(team_2);
		
		player.addChild(player_1);
		player.addChild(player_2);
		player.addChild(player_3);

		parent3.addChild(team);
		parent3.addChild(player);
		
		
		manager.persist(parent1);
		manager.persist(parent2);
		manager.persist(parent3);
	};
	
	@Override
	public void find(EntityManager manager, EntityTransaction transaction) {
		
		Category parent1 = manager.find(Category.class, parent_code[0]);
		this.print(0, parent1);
		System.out.println();
		
		Category parent2 = manager.find(Category.class, parent_code[1]);
		this.print(0, parent2);
		System.out.println();
		
		Category parent3 = manager.find(Category.class, parent_code[2]);
		this.print(0, parent3);
		System.out.println();
		
	};
	
	private void print(int depth, Category category) {
		String space = "";
		for (int i = 0; i < depth; i++) {
			space += "  ";
		}
		System.out.println(space + category.getValue() + "(" + category.getCode() + ")");
		
		if(category.getChildren().size() > 0) {
			depth += 1;
			for (Category child : category.getChildren()) {
				print(depth, child);
			}
		}
	}
	
	@Override
	public void change(EntityManager manager, EntityTransaction transaction) {
		Category parent3 = manager.find(Category.class, parent_code[2]);
		
		for (Category child1 : parent3.getChildren()) {
			
			if("Player".equals(child1.getValue())) {
				System.out.println("### Player ###");
				for (Category child2 : child1.getChildren()) {
					
					if("PlayerPosition".equals(child2.getValue())) {
						System.out.println("### PlayerPosition ###");
						for (Category child3 : child2.getChildren()) {
							
							if("persist_one".equals(child3.getValue())) {
								System.out.println("### persist ###");
								
								child3.changeValue("change");
								System.out.println("\n\n### CHANGE ###\n\n");
								break;
							}
						}
						break;
					}
					
				}
				break;
			}
		}
	}
	
	@Override
	public void remove(EntityManager manager, EntityTransaction transaction) {
		Category parent1 = manager.find(Category.class, parent_code[0]);
		Category parent2 = manager.find(Category.class, parent_code[1]);
		Category parent3 = manager.find(Category.class, parent_code[2]);
		
//		System.out.println(parent1);
//		this.print(0, parent1);
		manager.remove(parent1);
		manager.remove(parent2);
		manager.remove(parent3);
	}
}
