package com.tirmizee.api.data;

import com.tirmizee.jpa.specification.SearchPageable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExampleSearch2 extends SearchPageable {

	private String firstName;
	private String lastName;
	private String deptNo;
	private String deptName;
	
}
