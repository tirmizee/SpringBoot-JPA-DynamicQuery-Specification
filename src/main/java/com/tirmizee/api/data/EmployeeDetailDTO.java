package com.tirmizee.api.data;

import java.util.Date;

import lombok.Data;

@Data
public class EmployeeDetailDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String departmentNo;
	private String departmentName;
	
}
