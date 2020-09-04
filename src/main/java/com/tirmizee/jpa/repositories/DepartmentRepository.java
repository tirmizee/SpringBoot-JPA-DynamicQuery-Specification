package com.tirmizee.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tirmizee.jpa.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>, JpaSpecificationExecutor<Department> {

}
