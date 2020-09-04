package com.tirmizee.api.data;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeDetailDTO2 {

	private Long id;
	private String fname;
	private String lname;
	private Date bdate;
	private String departmentNo;
	private String departmentName;
	
}
