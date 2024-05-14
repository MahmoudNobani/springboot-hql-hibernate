package com.example.hql.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hql.dao.PhoneDao;
import com.example.hql.mappers.Mapper;
import com.example.hql.model.Employee;
import com.example.hql.model.Phone;
import com.example.hql.model.dto.PhoneDto;
import com.example.hql.service.PhoneService;

import jakarta.transaction.Transactional;

@Service
public class PhoneServiceImpl implements PhoneService{

    private PhoneDao phoneDao;
    private Mapper<Phone, PhoneDto> phoneMapper;

    public PhoneServiceImpl(PhoneDao phoneDao, Mapper<Phone, PhoneDto> phoneMapper) {
        this.phoneDao = phoneDao;
        this.phoneMapper = phoneMapper;
    }

    @Override
    @Transactional
    public List<Phone> get() {
        List<Phone> phones = phoneDao.get();
        return phones;

        // return phones.stream()
        //         .map(phoneMapper::mapTo)
        //         .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Phone get(int number) {
        return phoneDao.get(number);
    }

    @Override
    @Transactional
    public void save(Phone phone) {
        phoneDao.save(phone);
    }

    @Override
    @Transactional
    public void update(int number, Phone phone) {
        phoneDao.update(phone);
    }

    @Override
    @Transactional
    public void delete(int number) {
        phoneDao.delete(number);
    }

}
