package com.example.hql.service;

import java.util.List;

import com.example.hql.model.Phone;
import com.example.hql.model.dto.PhoneDto;

public interface PhoneService {
    List<Phone> get();
	
	Phone get(int number);
	
	void save(Phone phone);

    void update(int number, Phone phone);
	
	void delete(int number);
}
