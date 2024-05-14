package com.example.hql.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.example.hql.dao.EmployeeDao;
import com.example.hql.model.Employee;
import com.example.hql.model.Phone;

import jakarta.persistence.EntityManager;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{

    @Autowired
    private EntityManager entityManager;

    @Override
	public List<Employee> get() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Employee> query = currentSession.createQuery("from Employee", Employee.class);
		List<Employee> list = query.getResultList();
		return list;
	}

	public List<Phone> findAllPhonesByEmployeeId(Integer employeeId) {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("SELECT p FROM Phone p WHERE p.employee.id = :employeeId", Phone.class)
                      .setParameter("employeeId", employeeId)
                      .getResultList();
    }

	@Override
	public Employee get(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Employee employeeObj = currentSession.get(Employee.class, id);
		return employeeObj;
	}

	@Override
	public void save(Employee employee) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.persist(employee);
	}

    @Override
	public void update(Employee employee) {
		entityManager.merge(employee);
	}

	@Override
	public void delete(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Employee employeeObj = currentSession.get(Employee.class, id);
		currentSession.remove(employeeObj);
	}


}
