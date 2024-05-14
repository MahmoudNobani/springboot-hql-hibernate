package com.example.hql.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.hql.model.Phone;
import com.example.hql.model.dto.PhoneDto;
import com.example.hql.mappers.Mapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PhoneMapperImpl implements Mapper<Phone, PhoneDto>{
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PhoneDto mapTo(Phone phone) {
        return modelMapper.map(phone, PhoneDto.class);
    }

    @Override
    public Phone mapFrom(PhoneDto phoneDto) {
        return modelMapper.map(phoneDto, Phone.class);
    }
}
