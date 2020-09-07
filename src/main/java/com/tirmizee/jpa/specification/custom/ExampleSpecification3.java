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

import com.tirmizee.api.data.ExampleSearch3;
import com.tirmizee.jpa.entities.Department;
import com.tirmizee.jpa.entities.Employee;
import com.tirmizee.jpa.specification.SearchPageSpecification;

public class ExampleSpecification3 extends SearchPageSpecification<SearchCriteria<ExampleSearch3>, Employee> {

	private static final long serialVersionUID = 1L;

	public ExampleSpecification3(SearchCriteria<ExampleSearch3> serachPage) {
		super(serachPage);
	}

	@Override
	public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder,
			SearchCriteria<ExampleSearch3> search) {

		Join<Employee, Department> department = root.join("department", JoinType.INNER);
		
		ExampleSearch3 searchRequest = search.getSearch();
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (StringUtils.isNotEmpty(searchRequest.getFirstName())) {
			Predicate likeFirstName = criteriaBuilder.like(root.get("firstName"), "%" + searchRequest.getFirstName() + "%" );
			predicates.add(likeFirstName);
		}
		
		if (StringUtils.isNotEmpty(searchRequest.getLastName())) {
			Predicate likeLastName = criteriaBuilder.like(root.get("lastName"), "%" + searchRequest.getLastName() + "%" );
			predicates.add(likeLastName);
		}
		
		if (StringUtils.isNotEmpty(searchRequest.getDeptNo())) {
			Predicate eqDeptNo = criteriaBuilder.equal(department.get("deptNo"), searchRequest.getDeptNo());
			predicates.add(eqDeptNo);
		}
		
		if (StringUtils.isNotEmpty(searchRequest.getDeptName())) {
			Predicate likeDeptName = criteriaBuilder.like(department.get("deptName"),"%" + searchRequest.getDeptName() + "%" );
			predicates.add(likeDeptName);
		}
		
		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}

	@Override
	protected String sortProperty(String sortField) {
		switch (sortField) {
			case "fname" : return "firstName";
			case "lname" : return "lastName";
			case "departmentNo" : return "department.deptNo";
			case "departmentName" : return "department.deptName";
			default : return sortField;
		}	
	} 

}
