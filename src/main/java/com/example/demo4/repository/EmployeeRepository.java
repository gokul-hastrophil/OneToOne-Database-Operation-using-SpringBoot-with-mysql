package com.example.demo4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo4.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
}
