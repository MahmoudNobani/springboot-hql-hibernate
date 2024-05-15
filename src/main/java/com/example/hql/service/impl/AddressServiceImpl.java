package com.example.hql.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.example.hql.dao.AddressDao;
import com.example.hql.model.Address;
import com.example.hql.model.Employee;
import com.example.hql.service.AddressService;
import com.example.hql.service.EmployeeService;

import jakarta.transaction.Transactional;

@Service
public class AddressServiceImpl implements AddressService {

    AddressDao addressDao;

    @Autowired
    EmployeeService employeeService;

    public AddressServiceImpl(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Override
    @Transactional
    public List<Address> get() {
        return addressDao.get();
    }

    @Override
    @Transactional
    public Address get(int id) {
        return addressDao.get(id);
    }

    @Override
    @Transactional
    public void save(Address address) {
        addressDao.save(address);
        List<Employee> residents = new ArrayList<>(address.getResidents());
        for (Employee z : residents) {
            Employee x = employeeService.get(z.getId());
            List<Address> y = x.getAddresses();
            y.add(address);
            employeeService.update(x);
        }
    }

    @Override
    @Transactional
    public void update(Address address) {
        System.out.println("inside");
        System.out.println(address.getResidents());
        System.out.println();
        addressDao.update(address);
    }

    @Override
    @Transactional
    public void delete(int id) {
        //addressDao.delete(id);
        Address address = get(id);
        if (address != null){
            List<Employee> residents = new ArrayList<>(address.getResidents());
            for (Employee z : residents) {
                Employee x = employeeService.get(z.getId());
                List<Address> y = x.getAddresses();
                y.remove(address);
                employeeService.update(x);
            }
        }
        addressDao.delete(id);
    }

}
