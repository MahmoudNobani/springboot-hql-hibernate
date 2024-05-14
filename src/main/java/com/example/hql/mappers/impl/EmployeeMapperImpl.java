package com.example.hql.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.hql.model.Employee;
import com.example.hql.model.dto.EmployeeDto;
import com.example.hql.mappers.Mapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class EmployeeMapperImpl implements Mapper<Employee, EmployeeDto>{
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EmployeeDto mapTo(Employee employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Override
    public Employee mapFrom(EmployeeDto employeeDto) {
        // Employee employee = new Employee().builder().id(employeeDto.getId()).name(employeeDto.getName())
        // .gender(employeeDto.getGender()).department(employeeDto.getDepartment()).dob(employeeDto.getDob()).build();
        // return employee;
        return modelMapper.map(employeeDto, Employee.class);
    }
}
