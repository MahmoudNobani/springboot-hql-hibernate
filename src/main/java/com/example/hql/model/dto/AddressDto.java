package com.example.hql.model.dto;

import java.util.List;

import com.example.hql.model.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDto {

    private Integer id;
    
    private String street;
    
    private String city;

   //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<EmployeeDto> residents;
}
