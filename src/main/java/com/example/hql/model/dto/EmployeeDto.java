package com.example.hql.model.dto;

import java.sql.Date;
import java.util.List;

import com.example.hql.model.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {

    private Integer id;

    private String name;

    private String gender;

    private String department;

    private Date dob;
    
    private List<PhoneDto> phones;

    private List<AddressDto> addresses;
}
