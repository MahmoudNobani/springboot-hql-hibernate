package com.example.hql.dao;

import java.util.List;

import com.example.hql.model.Address;

public interface AddressDao {
    List<Address> get();
	
	Address get(int id);
	
	void save(Address address);

    void update(Address address);
	
	void delete(int id);
}
