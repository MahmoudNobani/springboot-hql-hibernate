package com.example.hql.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.hql.mappers.impl.PhoneMapperImpl;
import com.example.hql.model.Phone;
import com.example.hql.model.dto.PhoneDto;
import com.example.hql.service.PhoneService;

@RestController
@RequestMapping("/phone")
public class PhoneControllers {

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private PhoneMapperImpl phoneMapper;

    @PostMapping()
    public ResponseEntity<Phone> save(@RequestBody PhoneDto phoneDto) {
        Phone phone = phoneMapper.mapFrom(phoneDto);
        phoneService.save(phone);
        return new ResponseEntity<>(phone, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Phone>> get() {
        List<Phone> phones = phoneService.get();
        return new ResponseEntity<>(phones, HttpStatus.OK);
    }

    @GetMapping("/{number}")
    public ResponseEntity<Phone> get(@PathVariable int number) {
        Phone phone = phoneService.get(number);
        if (phone == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(phone, HttpStatus.OK);
    }

    @PutMapping("/{number}")
    public ResponseEntity<Void> update(@PathVariable int number, @RequestBody PhoneDto phoneDto) {
        Phone phone = phoneMapper.mapFrom(phoneDto);
        phone.setNumber(number);
        phoneService.update(number, phone);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{number}")
    public ResponseEntity<String> delete(@PathVariable int number) {
        phoneService.delete(number);
        return new ResponseEntity<>("Phone has been deleted with id: " + number, HttpStatus.NO_CONTENT);
    }
}
