package com.example.employeetesting.service;

import com.example.employeetesting.dto.EmployeeDTO;
import com.example.employeetesting.mapper.EmployeeMapper;
import com.example.employeetesting.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;


    @Override
    public List<EmployeeDTO> findBySalaryGreaterThan(double salary) {
        return employeeRepository.findBySalaryGreaterThan(salary).stream().map(employeeMapper::modelToDto).toList();
    }

    @Override
    public List<EmployeeDTO> findByAgeGreaterThan(int age) {
        return employeeRepository.findByAgeGreaterThan(age).stream().map(employeeMapper::modelToDto).toList();
    }

    @Override
    public List<EmployeeDTO> findByPosition(String position) {
        return employeeRepository.findByPosition(position).stream().map(employeeMapper::modelToDto).toList();
    }

    @Override
    public EmployeeDTO findTopByOrderBySalaryDesc() {
        return employeeMapper.modelToDto(employeeRepository.findTopByOrderBySalaryDesc());
    }

}
