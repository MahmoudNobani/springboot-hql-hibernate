package com.example.hql.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_address")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    
    @Column
    private String street;
    
    @Column
    private String city;

    
    @ManyToMany(mappedBy = "addresses", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Employee> residents;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getStreet() {
        return street;
    }


    public void setStreet(String street) {
        this.street = street;
    }


    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city;
    }


    public List<Employee> getResidents() {
        return residents;
    }


    public void setResidents(List<Employee> residents) {
        this.residents = residents;
    }


    @Override
    public String toString() {
        return "Address [id=" + id + ", street=" + street + ", city=" + city + "]";
    }

    
    
    // public void addResident(Employee employee) {
    //     if (residents == null) {
    //         residents = new ArrayList<>();
    //     }
    //     residents.add(employee);
    //     if (!employee.getAddresses().contains(this)) {
    //         employee.getAddresses().add(this);
    //     }
    // }

    

    
}
