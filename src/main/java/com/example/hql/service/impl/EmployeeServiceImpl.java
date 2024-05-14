package com.example.hql.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hql.model.Employee;
import com.example.hql.model.Phone;
import com.example.hql.model.dto.PhoneDto;
import com.example.hql.service.AddressService;
import com.example.hql.service.EmployeeService;
import com.example.hql.service.PhoneService;

import jakarta.transaction.Transactional;

import com.example.hql.dao.EmployeeDao;
import com.example.hql.dao.PhoneDao;
import com.example.hql.mappers.impl.AddressMapperImpl;
import com.example.hql.mappers.impl.PhoneMapperImpl;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDao employeeDao;
    private PhoneDao phoneDao;
    @Autowired
	private PhoneService phoneService;
	@Autowired
	private PhoneMapperImpl phoneMapper;


    public EmployeeServiceImpl(EmployeeDao employeeDao, PhoneDao phoneDao) {
        this.employeeDao = employeeDao;
        this.phoneDao = phoneDao;
    }

    @Override
    @Transactional
    public List<Employee> get() {
        return employeeDao.get();
    }

    @Override
    @Transactional
    public Employee get(int id) {
        return employeeDao.get(id);
    }

    @Override
    @Transactional
    public void save(Employee employee) {
        employeeDao.save(employee);
        if (employee.getPhones()!= null) {
            for (Phone phone : employee.getPhones()) {
				//Phone phoneObj = phoneMapper.mapFrom(phone);
				phone.setEmployee(employee);
				phoneService.save(phone);
            }
        }
    }

    @Override
    @Transactional
    public void update(Employee employee) {
        if (employee.getPhones()!= null) {
            for (Phone phone : employee.getPhones()) {
				//Phone phoneObj = phoneMapper.mapFrom(phone);
				phone.setEmployee(employee);
				//phoneService.save(phone);
            }
        }
        employeeDao.update(employee);
    }

    @Override
    @Transactional
    public void delete(int id) {     
        employeeDao.delete(id);
    }
    // public void delete(int id) {
    //     List<Phone> x = employeeDao.findAllPhonesByEmployeeId(id);
    //     if (x != null){
    //         for (Phone phone : x) {
    //             phoneDao.delete(phone.getNumber());
    //         }
    //     }       
    //     employeeDao.delete(id);
    // }

}
