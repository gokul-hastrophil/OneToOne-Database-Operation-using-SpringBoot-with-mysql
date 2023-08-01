package com.example.demo4.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")


public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "add_id")
    private Long addressId;

    @Column(name =  "city")
    private String city;

    @Column(name = "addressType")
    private String addressType;

    public Address() {
    }

    public Address(Long addressId, String city, String addressType) {
        this.addressId = addressId;
        this.city = city;
        this.addressType = addressType;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    @Override
    public String toString() {
        return "Address [addressId=" + addressId + ", city=" + city + ", addressType=" + addressType + "]";
    }


    @OneToOne(mappedBy = "address")
    private Employee employee;


    
    


}
