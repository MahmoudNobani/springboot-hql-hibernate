package com.example.hql.service;

import java.util.List;

import com.example.hql.model.Employee;

public interface EmployeeService {
    List<Employee> get();
	
	Employee get(int id);
	
	void save(Employee employee);

    void update(Employee employee);
	
	void delete(int id);
}
