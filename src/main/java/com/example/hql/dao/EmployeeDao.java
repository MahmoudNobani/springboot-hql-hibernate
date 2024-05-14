package com.example.hql.dao;

import java.util.List;

import com.example.hql.model.Employee;
import com.example.hql.model.Phone;

public interface EmployeeDao {
    List<Employee> get();
	
	Employee get(int id);
	
	void save(Employee employee);

    void update(Employee employee);
	
	void delete(int id);

	List<Phone> findAllPhonesByEmployeeId(Integer employeeId);
}
