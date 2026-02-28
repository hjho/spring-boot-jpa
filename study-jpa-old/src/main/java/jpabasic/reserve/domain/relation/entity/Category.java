package jpabasic.reserve.domain.relation.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Category {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private Long code;
    
    private String value;
    
    private String description;
 
    @ManyToOne(fetch = FetchType.LAZY)
    // @MapsId("parent_code")
    @JoinColumn(name = "parent_code")
    // @MapsId: 외래 키를 기본 키로 매핑 + @Id
    // @JoinColumn: 자신만의 고유한 기본 키(@Id)를 가지고 있고, 부모 테이블을 참조하는 외래 키를 가질 때 사용합니다.
    // MapsId 를 이용하여 계층을 만들면 parent_code 가 매핑이 안됨.
    private Category parent;
    
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Category> children = new ArrayList<>();
    
    protected Category() {}
    public Category(String value, String description) {
    	this.value = value;
    	this.description = description;
    }
    
    public void changeValue(String value) {
    	this.value = value;
    }
    public boolean addChild(Category category) {
    	category.parent = this;
    	return children.add(category);
    }
    /**
     * hibernate의 Flush 순서
     * 1. OrphanRemovalAction
     * 2. AbstractEntityInsertAction
     * 3. EntityUpdateAction
     * 4. QueuedOperationCollectionAction
     * 5. CollectionRemoveAction
     * 6. CollectionUpdateAction
     * 7. CollectionRecreateAction
     * 8. EntityDeleteAction
     */
    public boolean removeChild(Category category) {
    	return children.remove(category);
    }
    public boolean clearAndAddChildren(List<Category> children) {
    	this.children.clear();
    	return this.children.addAll(children);
    }
    public Long getCode() {
    	return code;
    }
    public String getValue() {
    	return value;
    }
    public String getDescription() {
    	return description;
    }
    public Category getParent() {
    	return parent;
    }
    public List<Category> getChildren() {
    	return children;
    }
    
    @Override
    public String toString() {
    	Map<String, Object> map = new LinkedHashMap<String, Object>();
    	if(parent != null) {
    		map.put("parent_code", parent.getCode());
    	}
    	map.put("code", code);
    	map.put("value", value);
    	map.put("description", description);
        return new Gson().toJson(map);
    }
    
}
