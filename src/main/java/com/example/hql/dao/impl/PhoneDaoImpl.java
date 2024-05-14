package com.example.hql.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.hql.dao.PhoneDao;
import com.example.hql.model.Employee;
import com.example.hql.model.Phone;

import jakarta.persistence.EntityManager;

@Repository
public class PhoneDaoImpl implements PhoneDao{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Phone> get() {
        Session currentSession = entityManager.unwrap(Session.class);
		Query<Phone> query = currentSession.createQuery("from Phone", Phone.class);
		List<Phone> list = query.getResultList();
        //System.out.println(list);
		return list;
    }

    @Override
    public Phone get(int number) {
        Session currentSession = entityManager.unwrap(Session.class);
		Phone phoneObj = currentSession.get(Phone.class, number);
		return phoneObj;
    }

    @Override
    public void save(Phone phone) {
        System.out.println(phone);
        //Session currentSession = entityManager.unwrap(Session.class);
		entityManager.persist(phone);
    }

    @Override
    public void update(Phone phone) {
        System.out.println(phone);
        Session currentSession = entityManager.unwrap(Session.class);
        // Employee emp = currentSession.get(Employee.class, phone.getEmployee().getId());
        // phone.setEmployee(emp);
        currentSession.merge(phone);
    }

    @Override
    public void delete(int number) {
        Session currentSession = entityManager.unwrap(Session.class);
		Phone phoneObj = currentSession.get(Phone.class, number);
		currentSession.remove(phoneObj);
    }

}
