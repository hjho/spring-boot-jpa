package com.document.study.jpa.business.repository;

import java.util.List;

import com.document.study.jpa.entity.Player;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayerRepositoryImpl implements PlayerRepositoryCustom {

	
	private final EntityManager entityManager;
	
	
	@Override
	public void persist(Player player) {
		entityManager.persist(player);
	}

	
	@Override
	public List<Player> findJpql() {
		String sql = """
				select p from Player p
			             left join fetch p.card c
			             left join fetch p.position s
		""";
		TypedQuery<Player> query = entityManager.createQuery(sql, Player.class);
		
		return query.getResultList();
	}
	
	@Override
	public Player findJpqlOne(String code) {
		String sql = """
				select p from Player p
			             left join fetch p.card c
			             left join fetch p.position s
			            where p.code = :code
		""";
		TypedQuery<Player> query = entityManager.createQuery(sql, Player.class);
		query.setParameter("code", code);
		
		return query.getSingleResultOrNull();
	}


	@Override
	public List<Player> findCriteria() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Player> query = builder.createQuery(Player.class);
		Predicate predicate = builder.conjunction();
		
		Root<Player> root = query.from(Player.class);
		
		predicate = builder.and(predicate, builder.greaterThanOrEqualTo(root.get("age"), 21L));
		
		query
			.select(root)
			.where(predicate)
			.orderBy(
					builder.asc(root.get("name"))
			);
		
		TypedQuery<Player> query1 = entityManager.createQuery(query);
		return query1.getResultList();
	}
	
	@Override
	public Player findCriteriaOne(String code) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Player> query = builder.createQuery(Player.class);
		Predicate predicate = builder.conjunction();
		
		Root<Player> root = query.from(Player.class);
		
		predicate = builder.and(predicate, builder.equal(root.get("code"), code));
		predicate = builder.and(predicate, builder.greaterThanOrEqualTo(root.get("age"), 21L));
		
		query
			.select(root)
			.where(predicate)
			.orderBy(
					builder.asc(root.get("name"))
			);
		
		TypedQuery<Player> query1 = entityManager.createQuery(query);
		return query1.getSingleResultOrNull();
	}

}
