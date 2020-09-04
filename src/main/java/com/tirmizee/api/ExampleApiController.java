package com.tirmizee.api;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tirmizee.api.data.EmployeeDetailDTO;
import com.tirmizee.api.data.EmployeeDetailDTO2;
import com.tirmizee.api.data.ExampleSearch1;
import com.tirmizee.api.data.ExampleSearch2;
import com.tirmizee.api.data.ExampleSearch3;
import com.tirmizee.jpa.entities.Employee;
import com.tirmizee.jpa.repositories.EmployeeRepository;
import com.tirmizee.jpa.specification.custom.ExampleSpecification1;
import com.tirmizee.jpa.specification.custom.ExampleSpecification2;
import com.tirmizee.jpa.specification.custom.ExampleSpecification3;
import com.tirmizee.jpa.specification.custom.SearchCriteria;

@RestController
@RequestMapping(path = "/api")
public class ExampleApiController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping(path = "/employee/list")
	public List<EmployeeDetailDTO> exampleOne(ExampleSearch1 searchParam){
		
		ExampleSpecification1 specificationOne = new ExampleSpecification1(searchParam);
		
		List<Employee> employees = employeeRepository.findAll(specificationOne);
		
		// transform Employee to EmployeeDetailDTO
		Function<Employee,EmployeeDetailDTO> converter = source -> {
			EmployeeDetailDTO target = new EmployeeDetailDTO();
			target.setId(source.getId());
			target.setFirstName(source.getFirstName());
			target.setLastName(source.getLastName());
			target.setDepartmentNo(source.getDeptNo());
			target.setDepartmentName(source.getDepartment().getDeptName());
			return target;
		};
		List<EmployeeDetailDTO> employeeDetails = employees.stream().map(converter).collect(Collectors.toList());
		
		return employeeDetails;
	}
	
	@GetMapping(path = "/employee/page")
	public Page<EmployeeDetailDTO> exampleTwo(ExampleSearch2 searchPage){
		
		ExampleSpecification2 specificationTwo = new ExampleSpecification2(searchPage);
		
		Page<Employee> page = employeeRepository.findAll(specificationTwo, specificationTwo.getPageable());
		
		// transform Employee to EmployeeDetailDTO
		Function<Employee, EmployeeDetailDTO> converter = source -> {
			EmployeeDetailDTO target = new EmployeeDetailDTO();
			target.setId(source.getId());
			target.setFirstName(source.getFirstName());
			target.setLastName(source.getLastName());
			target.setDepartmentNo(source.getDeptNo());
			target.setDepartmentName(source.getDepartment().getDeptName());
			return target;
		};
		
		return page.map(converter);
	}
	
	@PostMapping(path = "/employee/page")
	public Page<EmployeeDetailDTO2> exampleThree(@RequestBody SearchCriteria<ExampleSearch3> searchCriteria){
		
		ExampleSpecification3 specificationThree = new ExampleSpecification3(searchCriteria);
		
		Page<Employee> page = employeeRepository.findAll(specificationThree, specificationThree.getPageable());
		
		// transform Employee to EmployeeDetailDTO
		Function<Employee, EmployeeDetailDTO2> converter = source -> {
			EmployeeDetailDTO2 target = new EmployeeDetailDTO2();
			target.setId(source.getId());
			target.setFname(source.getFirstName());
			target.setLname(source.getLastName());
			target.setDepartmentNo(source.getDeptNo());
			target.setDepartmentName(source.getDepartment().getDeptName());
			return target;
		};
		
		return page.map(converter);
	}
	
}
