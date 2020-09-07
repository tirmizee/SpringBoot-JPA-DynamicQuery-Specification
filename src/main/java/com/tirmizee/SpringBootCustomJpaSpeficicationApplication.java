package com.tirmizee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.github.javafaker.Faker;
import com.tirmizee.jpa.entities.Department;
import com.tirmizee.jpa.entities.Employee;
import com.tirmizee.jpa.repositories.DepartmentRepository;
import com.tirmizee.jpa.repositories.EmployeeRepository;

@SpringBootApplication
public class SpringBootCustomJpaSpeficicationApplication implements CommandLineRunner {

	@Autowired
	ApplicationContext context;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootCustomJpaSpeficicationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		 Mock Data
		
		EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);
		DepartmentRepository departmentRepository = context.getBean(DepartmentRepository.class);
		
		Faker faker = Faker.instance();
		
		List<Department> departments = new ArrayList<>();
		for (int i = 1; i <= 9; i++) {
			Department department = new Department();
			department.setDeptNo("D00" + i);
			department.setDeptName(faker.team().name());
			department.setCreateDate(new Date());
			departments.add(department);
		}
		
		departments = departmentRepository.saveAll(departments);
		
		List<Employee> employees = new ArrayList<>();
		for (int i = 0; i < 200; i++) {
			
			Employee employee = new Employee();
			employee.setFirstName(faker.name().firstName());
			employee.setLastName(faker.name().lastName());
			employee.setPhone(faker.phoneNumber().phoneNumber());
			employee.setBirthDate(faker.date().birthday());
			employee.setDepartment(departments.get(new Random().nextInt(departments.size())));
			employees.add(employee);
		}
		
		employeeRepository.saveAll(employees);
		
	}

}
