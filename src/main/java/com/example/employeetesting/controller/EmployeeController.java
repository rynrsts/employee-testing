package com.example.employeetesting.controller;

import com.example.employeetesting.dto.EmployeeDTO;
import com.example.employeetesting.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/salary")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesGreaterThanSalary(@RequestParam Double salary) {
        return new ResponseEntity<>(employeeService.findBySalaryGreaterThan(salary), HttpStatus.OK);
    }

    @GetMapping("/age")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesGreaterThanAge(@RequestParam Integer age) {
        return new ResponseEntity<>(employeeService.findByAgeGreaterThan(age), HttpStatus.OK);
    }

    @GetMapping("/position")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByPosition(@RequestParam String position) {
        return new ResponseEntity<>(employeeService.findByPosition(position), HttpStatus.OK);
    }

    @GetMapping("/salary/top")
    public ResponseEntity<EmployeeDTO> getEmployeeWithHighestSalary() {
        return new ResponseEntity<>(employeeService.findTopByOrderBySalaryDesc(), HttpStatus.OK);
    }

}
