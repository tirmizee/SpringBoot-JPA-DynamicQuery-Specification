# SpringBoot-Custom-JPA-Specification

This is an easy-to-use example of searching sorting and paging with Spring Data JPA specification.

### Sample tables

- Employee 
- Department

<p align="center">
  <img src="https://user-images.githubusercontent.com/15135199/92202239-bf529c00-eea8-11ea-9083-7eedd7c29722.JPG" >
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
	
#### Example 1

- Request example

	GET method 
	URl : http://localhost:8000/api/employee/list?deptNo=&firstName=&lastName=&deptName
	
- Response example
	
	[
		{
		    "id": null,
		    "firstName": null,
		    "lastName": null,
		    "birthDate": null,
		    "departmentNo": null,
		    "departmentName": null
		}
	]

- Demo 

<p align="center">
  <img src="https://user-images.githubusercontent.com/15135199/92250138-4af21a00-eef5-11ea-8055-920b9ae00f5c.gif" width="800">
</p>


### Dependencies

- Spring Data JPA
- Faker (generate fake data )