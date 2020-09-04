package com.tirmizee.jpa.specification.custom;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.tirmizee.api.data.ExampleSearch1;
import com.tirmizee.jpa.entities.Department;
import com.tirmizee.jpa.entities.Employee;
import com.tirmizee.jpa.specification.SearchSpecification;

public class ExampleSpecification1 extends SearchSpecification<ExampleSearch1, Employee> {

	private static final long serialVersionUID = 1L;

	public ExampleSpecification1(ExampleSearch1 search) {
		super(search);
	}

	@Override
	public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, ExampleSearch1 searchBody) {
		
		Join<Employee, Department> department = root.join("department", JoinType.INNER);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (StringUtils.isNotEmpty(searchBody.getFirstName())) {
			Predicate likeFirstName = criteriaBuilder.like(root.get("firstName"), "%" + searchBody.getFirstName() + "%" );
			predicates.add(likeFirstName);
		}
		
		if (StringUtils.isNotEmpty(searchBody.getLastName())) {
			Predicate likeLastName = criteriaBuilder.like(root.get("lastName"), "%" + searchBody.getLastName() + "%" );
			predicates.add(likeLastName);
		}
		
		if (StringUtils.isNotEmpty(searchBody.getDeptNo())) {
			Predicate eqDeptNo = criteriaBuilder.equal(department.get("deptNo"), searchBody.getDeptNo());
			predicates.add(eqDeptNo);
		}
		
		if (StringUtils.isNotEmpty(searchBody.getDeptName())) {
			Predicate likeDeptName = criteriaBuilder.like(department.get("deptName"),"%" + searchBody.getDeptName() + "%" );
			predicates.add(likeDeptName);
		}
		
		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}

}
