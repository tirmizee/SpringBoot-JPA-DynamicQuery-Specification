package com.tirmizee.jpa.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NaturalId;

import lombok.Data;

@Data
@Entity
@Table(name = "DEPARTMENT")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	
	@NaturalId
	@Column(name = "DEPT_NO")
	private String deptNo;
	
	@Column(name = "DEPT_NAME")
	private String deptName;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE")
	private Date createDate;
	
	@OneToMany(mappedBy = "department")
	private List<Employee> employees;
	
}
