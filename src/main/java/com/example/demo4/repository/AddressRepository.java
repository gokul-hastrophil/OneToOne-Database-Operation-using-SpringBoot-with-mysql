package com.example.demo4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo4.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    
}
