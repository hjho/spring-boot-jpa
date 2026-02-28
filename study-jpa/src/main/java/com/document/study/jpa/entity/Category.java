package com.document.study.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private Long code;
    
    private String value;
    
    private String description;
 
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_code")
    private Category parent;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Category> children = new ArrayList<>();
    
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
    public boolean removeChild(Category category) {
    	return children.remove(category);
    }
    public boolean clearAndAddChildren(List<Category> children) {
    	this.children.clear();
    	return this.children.addAll(children);
    }
}
