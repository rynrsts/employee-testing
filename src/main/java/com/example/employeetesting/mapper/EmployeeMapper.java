package com.example.employeetesting.mapper;

import com.example.employeetesting.dto.EmployeeDTO;
import com.example.employeetesting.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDTO modelToDto(Employee employee);

}
