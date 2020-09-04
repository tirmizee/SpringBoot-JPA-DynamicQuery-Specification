package com.tirmizee.jpa.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import lombok.Data;

/**
 * @author Pratya Yeekhaday
 * 
 * @param <B> Object type of search
 * @param <T> Entity
 */

@Data
public abstract class SearchSpecification<S,T> implements Specification<T> {

	private static final long serialVersionUID = 1L;
	
	private S search;
	
	public SearchSpecification(S search) {
		this.search = search;
	}
	
	public abstract Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, S searchBody);
	
	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		return this.toPredicate(root, query, criteriaBuilder, search);
	}
	
}
