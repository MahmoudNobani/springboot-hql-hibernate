package com.example.hql.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.hql.mappers.Mapper;
import com.example.hql.model.Employee;
import com.example.hql.model.dto.EmployeeDto;
import com.example.hql.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private Mapper<Employee, EmployeeDto> employeeMapper;

    @PostMapping()
    public ResponseEntity<EmployeeDto> save(@RequestBody EmployeeDto employeeDto) {
        Employee employeeObj = employeeMapper.mapFrom(employeeDto);
        employeeService.save(employeeObj);
        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Employee>> get() {
        List<Employee> employees = employeeService.get();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> get(@PathVariable int id) {
        Employee employeeObj = employeeService.get(id);
        if (employeeObj == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeObj, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Employee employeeObj) {
        employeeObj.setId(id);
        employeeService.update(employeeObj);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        employeeService.delete(id);
        return new ResponseEntity<>("Employee has been deleted with id: " + id, HttpStatus.NO_CONTENT);
    }
}
