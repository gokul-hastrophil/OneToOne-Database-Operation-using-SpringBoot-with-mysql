package com.example.demo4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo4.model.MainModel;

public interface MainRepository extends JpaRepository<MainModel, Long> {
    
}
