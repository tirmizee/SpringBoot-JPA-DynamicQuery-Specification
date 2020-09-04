package com.tirmizee.jpa.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "EMPLOYEE")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "DEPT_NO", insertable = false, updatable = false)
	private String deptNo;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "MID_NAME")
	private String midName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "PHONE")
	private String phone;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BIRTH_DATE")
	private Date birthDate;
	
	@ManyToOne
	@JoinColumn(name = "DEPT_NO", referencedColumnName = "DEPT_NO")
	private Department department;
	
}
