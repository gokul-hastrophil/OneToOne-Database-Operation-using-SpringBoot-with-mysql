package com.example.demo4.model;


// import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
// import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_details")

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "emp_id")
    private Long empId;

    @Column(name = "empName")
    private String empName;

    @Column(name = "empAge")
    private String empAge;


    public Employee() {
    }

    public Employee(Long empId, String empName, String empAge, Address address) {
        this.empId = empId;
        this.empName = empName;
        this.empAge = empAge;
        this.address = address;
    }


    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpAge() {
        return empAge;
    }

    public void setEmpAge(String empAge) {
        this.empAge = empAge;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    


    @Override
    public String toString() {
        return "Employee [empId=" + empId + ", empName=" + empName + ", empAge=" + empAge + ", address=" + address
                + "]";
    }




    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "add_id")
    private Address address;


    // @OneToMany(cascade = CascadeType.ALL)
    // @JoinColumn(name = "fk_emp_id", referencedColumnName = "emp_id")
    // private List<Address> address;

    
}
