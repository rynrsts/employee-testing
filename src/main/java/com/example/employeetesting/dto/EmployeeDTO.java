package com.example.employeetesting.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDTO {

    public Long id;
    private String name;
    private int age;
    private Double salary;
    private String position;

}
