package com.example.hql.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.example.hql.model.Address;
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
    @Lazy
	private AddressService addressService;
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
        List<Address> existingAddresses = new ArrayList<>();
        if (employee.getAddresses() != null) {
            List<Address> addresses = employee.getAddresses();
            for (int i = 0; i < addresses.size(); i++) {
                Address address = addresses.get(i);
                if(address.getId() != null && addressService.get(address.getId()) != null){
                    existingAddresses.add(address);
                    employee.getAddresses().remove(address);
                }
            }
        }
        
        System.out.println(existingAddresses);
        System.out.println(employee.getAddresses());
        
        employeeDao.save(employee);
        if (employee.getPhones()!= null) {
            for (Phone phone : employee.getPhones()) {
				//Phone phoneObj = phoneMapper.mapFrom(phone);
				phone.setEmployee(employee);
				phoneService.save(phone);
            }
        }
        if(existingAddresses != null){
            employee.getAddresses().add(existingAddresses.get(0));
            System.out.println(employee.getAddresses());
            employeeDao.update(employee);
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
