package com.example.hql.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.hql.mappers.Mapper;
import com.example.hql.model.Address;
import com.example.hql.model.dto.AddressDto;
import com.example.hql.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;
    
    @Autowired
    private Mapper<Address, AddressDto> addressMapper;

    @PostMapping()
    public ResponseEntity<Address> save(@RequestBody AddressDto addressDto) {
        Address address = addressMapper.mapFrom(addressDto);
        addressService.save(address);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Address>> get() {
        List<Address> addresses = addressService.get();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> get(@PathVariable int id) {
        Address address = addressService.get(id);
        if (address == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Address address) {
        address.setId(id);
        addressService.update(address);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        addressService.delete(id);
        return new ResponseEntity<>("Address has been deleted with id: " + id, HttpStatus.NO_CONTENT);
    }
}
