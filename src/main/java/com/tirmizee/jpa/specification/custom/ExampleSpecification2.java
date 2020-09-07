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

import com.tirmizee.api.data.ExampleSearch2;
import com.tirmizee.jpa.entities.Department;
import com.tirmizee.jpa.entities.Employee;
import com.tirmizee.jpa.specification.SearchPageSpecification;

public class ExampleSpecification2 extends SearchPageSpecification<ExampleSearch2, Employee> {

	private static final long serialVersionUID = 1L;

	public ExampleSpecification2(ExampleSearch2 serachPage) {
		super(serachPage);
	}

	@Override
	public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder,
			ExampleSearch2 search) {
		
		Join<Employee, Department> department = root.join("department", JoinType.INNER);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (StringUtils.isNotEmpty(search.getFirstName())) {
			Predicate likeFirstName = criteriaBuilder.like(root.get("firstName"), "%" + search.getFirstName() + "%" );
			predicates.add(likeFirstName);
		}
		
		if (StringUtils.isNotEmpty(search.getLastName())) {
			Predicate likeLastName = criteriaBuilder.like(root.get("lastName"), "%" + search.getLastName() + "%" );
			predicates.add(likeLastName);
		}
		
		if (StringUtils.isNotEmpty(search.getDeptNo())) {
			Predicate eqDeptNo = criteriaBuilder.equal(department.get("deptNo"), search.getDeptNo());
			predicates.add(eqDeptNo);
		}
		
		if (StringUtils.isNotEmpty(search.getDeptName())) {
			Predicate likeDeptName = criteriaBuilder.like(department.get("deptName"),"%" + search.getDeptName() + "%" );
			predicates.add(likeDeptName);
		}
		
		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}
	
	@Override
	protected String sortProperty(String sortField) {
		
		if (sortField == null) return "id"; // default if sortField be null
		
		switch (sortField) {
			case "deptNo" : return "department.deptNo";
			case "deptName" : return "department.deptName";
			default : return sortField;
		}
		
	}

}
