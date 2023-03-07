package com.example.employeetesting.service;

import com.example.employeetesting.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> findBySalaryGreaterThan(double salary);

    List<EmployeeDTO> findByAgeGreaterThan(int age);

    List<EmployeeDTO> findByPosition(String position);

    EmployeeDTO findTopByOrderBySalaryDesc();

}
