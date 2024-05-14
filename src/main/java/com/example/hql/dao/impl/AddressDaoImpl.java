package com.example.hql.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.hql.dao.AddressDao;
import com.example.hql.mappers.impl.AddressMapperImpl;
import com.example.hql.mappers.impl.EmployeeMapperImpl;
import com.example.hql.model.Address;
import com.example.hql.model.Employee;
import com.example.hql.model.dto.EmployeeDto;

import jakarta.persistence.EntityManager;

@Repository
public class AddressDaoImpl implements AddressDao{

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private EmployeeMapperImpl employeeMapper;

    @Override
    public List<Address> get() {
        Session currentSession = entityManager.unwrap(Session.class);
		Query<Address> query = currentSession.createQuery("from Address", Address.class);
		List<Address> list = query.getResultList();
		return list;
    }

    @Override
    public Address get(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
		Address address = currentSession.get(Address.class, id);
		return address;
    }

    @Override
    public void save(Address address) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(address);
        // for (Employee emp :  address.getResidents()){
        //     Employee empO = currentSession.get(Employee.class, emp.getId());
        //     EmployeeDto empD = employeeMapper.mapTo(empO);
        //     System.out.println(empD);
        //     empO.addAddress(address);
        //     //entityManager.merge(empO);
        // }
		//currentSession.persist(address);
    }

    @Override
    public void update(Address address) {
        entityManager.merge(address);
    }

    @Override
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
		Address address = currentSession.get(Address.class, id);
		currentSession.remove(address);
    }

}
