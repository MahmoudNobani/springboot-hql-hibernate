package com.example.hql.service;

import java.util.List;

import com.example.hql.model.Address;

public interface AddressService {
    List<Address> get();
	
	Address get(int id);
	
	void save(Address address);

    void update(Address address);
	
	void delete(int id);
}
