package com.example.hql.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.hql.mappers.Mapper;
import com.example.hql.model.Address;
import com.example.hql.model.dto.AddressDto;

@Component
public class AddressMapperImpl implements Mapper<Address, AddressDto>{
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AddressDto mapTo(Address address) {
        return modelMapper.map(address, AddressDto.class);
    }

    @Override
    public Address mapFrom(AddressDto addressDto) {
        return modelMapper.map(addressDto, Address.class);
    }
}
