package com.example.hql.dao;

import java.util.List;
import com.example.hql.model.Phone;

public interface PhoneDao {
    List<Phone> get();
	
	Phone get(int number);
	
	void save(Phone phone);

    void update(Phone phone);
	
	void delete(int number);
}
