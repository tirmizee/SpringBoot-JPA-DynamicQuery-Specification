# SpringBoot-Custom-JPA-Specification

This is an easy-to-use example of searching sorting and paging with Spring Data JPA specification.

### Sample tables

- Employee 
- Department

<p align="center">
  <img src="https://user-images.githubusercontent.com/15135199/92202239-bf529c00-eea8-11ea-9083-7eedd7c29722.JPG" width="700">
</p>

### Configuration properties

change properties to your configuration

	## SERVER PORT
	server.port=8000
	
	## CONNECTION 
	spring.datasource.url=jdbc:mysql://localhost:3306/demo?useSSL=false&allowPublicKeyRetrieval=true&rewriteBatchedStatements=true
	spring.datasource.username=root
	spring.datasource.password=root
	spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
	
	## CONNECTION POOL
	spring.datasource.hikari.connection-timeout=600000
	spring.datasource.hikari.minimum-idle=2
	spring.datasource.hikari.maximum-pool-size=5
	spring.datasource.hikari.idle-timeout=3000000
	spring.datasource.hikari.max-lifetime=3000000
	spring.datasource.hikari.auto-commit=true
	spring.datasource.hikari.pool-name=MYSQL-DEMO-POOL
	
	## JPA HIBERNATE
	spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
	spring.jpa.properties.hibernate.hbm2ddl.auto=update
	spring.jpa.properties.hibernate.show_sql=true
	spring.jpa.properties.hibernate.use_sql_comments=true
	spring.jpa.properties.hibernate.format_sql=true
	spring.jpa.properties.hibernate.type=trace
	
	## LOGGING
	logging.level.com.zaxxer.hikari.HikariConfig=DEBUG
	logging.level.com.zaxxer.hikari=TRACE
	
#### Example 

- Example searching list of Employee.

		GET method 
		URl : http://localhost:8000/api/employee/list?deptNo=&firstName=&lastName=&deptName


<p align="center">
  <img src="https://user-images.githubusercontent.com/15135199/92250138-4af21a00-eef5-11ea-8055-920b9ae00f5c.gif" width="800">
</p>



- Example searching, sorting and paging for GET method.

		GET method 
		URl : http://localhost:8000/api/employee/page?page=0&sort=desc&sortField=deptName&firstName=Yos&lastName&deptNo=D002&deptName=Ar&size=20
	
<p align="center">
  <img src="https://user-images.githubusercontent.com/15135199/92253213-6ced9b80-eef9-11ea-9e2b-a6426f27c9ac.gif" width="800">
</p>


- Example searching, sorting and paging for POST method.

		POST method 
		URl : http://localhost:8000/api/employee/page
		Request body :
		{
		    "page" : 0,
		    "size" : "10",
		    "sort" : "asc",
		    "sortField" : "id",
		    "search" : {
			"firstName" : null,
			"lastName" : null,
			"deptNo" : null,
			"deptName" : null
		    }
		}

<p align="center">
  <img src="https://user-images.githubusercontent.com/15135199/92255946-387bde80-eefd-11ea-8a21-843b84d69961.gif" width="800">
</p>


### How to implement

1. Extend JpaSpecificationExecutor in your repository

		public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {}

2. Create custom specification class and extends SearchSpecification and override toPredicate method.

		public class ExampleSpecification1 extends SearchSpecification<ExampleSearch1, Employee> {

			private static final long serialVersionUID = 1L;

			public ExampleSpecification1(ExampleSearch1 search) {
				super(search);
			}

			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, ExampleSearch1 searchBody) {

				.....
			}
	
		}


  if you want to use paging and sorting extends SearchPageSpecification instead.

		public class ExampleSpecification2 extends SearchPageSpecification<ExampleSearch2, Employee> {

			private static final long serialVersionUID = 1L;

			public ExampleSpecification2(ExampleSearch2 serachPage) {
				super(serachPage);
			}

			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder,
					ExampleSearch2 searchBody) {

				....
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


  SearchPageSpecification provide getPageable function for create Pagable.

<p align="center">
  <img src="https://user-images.githubusercontent.com/15135199/92261597-e12d3c80-ef03-11ea-9c39-eb68dc145d63.JPG" width="800">
</p>


### Dependencies

- Spring Data JPA
- Faker (generate fake data )

### Demo

	mvn package
	java -jar SpringBoot-Custom-JPA-Speficication-0.0.1.jar
