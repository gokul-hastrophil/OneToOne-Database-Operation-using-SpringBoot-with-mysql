package com.example.demo4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo4.model.Employee;
import com.example.demo4.repository.EmployeeRepository;



@RestController
@RequestMapping("/api")

public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/employee")
    public ResponseEntity<String> saveEmployee(@RequestBody List<Employee> empData){
      employeeRepository.saveAll(empData);
      return ResponseEntity.ok("Data Saved");
    }

    @GetMapping("/show_data")
    public List<Employee> getAllTutorials(){
        return (List<Employee>) employeeRepository.findAll();    
    }

    @DeleteMapping("/delete_data")
    public ResponseEntity<HttpStatus> deleteAllTutorial(){
        employeeRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
