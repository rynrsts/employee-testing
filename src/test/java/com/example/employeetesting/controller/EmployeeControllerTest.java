package com.example.employeetesting.controller;

import com.example.employeetesting.dto.EmployeeDTO;
import com.example.employeetesting.mapper.EmployeeMapper;
import com.example.employeetesting.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Comparator;
import java.util.List;

@WebMvcTest
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private EmployeeMapper employeeMapper;

    private EmployeeDTO aliDTO, dondonDTO, michaelDTO, rDTO, ravenDTO;
    private List<EmployeeDTO> employeeList;


    @BeforeEach
    public void setup() {
        aliDTO = EmployeeDTO
                .builder()
                .name("Ali")
                .age(25)
                .salary(40000d)
                .position("Jr. Software Engineer")
                .build();
        dondonDTO = EmployeeDTO
                .builder()
                .name("Dondon")
                .age(26)
                .salary(200000d)
                .position("Senior Software Engineer")
                .build();
        michaelDTO = EmployeeDTO
                .builder()
                .name("Michael")
                .age(22)
                .salary(110000d)
                .position("Software Engineer")
                .build();
        rDTO = EmployeeDTO
                .builder()
                .name("R")
                .age(23)
                .salary(70000d)
                .position("Jr. Software Engineer")
                .build();
        ravenDTO = EmployeeDTO
                .builder()
                .name("Raven")
                .age(23)
                .salary(60000d)
                .position("Jr. Software Engineer")
                .build();
        employeeList = List.of(aliDTO, dondonDTO, michaelDTO, rDTO, ravenDTO);
    }

    @Test
    @DisplayName("When getEmployeesGreaterThanSalary() is executed, then return employees greater than given salary")
    public void testGetEmployeesGreaterThanSalary() throws Exception {
        double salary = 100000d;
        Mockito
                .when(employeeService.findBySalaryGreaterThan(salary))
                .thenReturn(employeeList.stream().filter(employee -> employee.getSalary() > salary).toList());

        String expectedJSONString = JSONArray.toJSONString(List.of(dondonDTO, michaelDTO));
        mockMvc
                .perform(MockMvcRequestBuilders.get("/employee/salary?salary=" + salary)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedJSONString));
    }

    @Test
    @DisplayName("When getEmployeesGreaterThanAge() is executed, then return employees greater than given age")
    public void testGetEmployeesGreaterThanAge() throws Exception {
        int age = 24;
        Mockito
                .when(employeeService.findByAgeGreaterThan(age))
                .thenReturn(employeeList.stream().filter(employee -> employee.getAge() > age).toList());

        String expectedJSONString = JSONArray.toJSONString(List.of(aliDTO, dondonDTO));
        mockMvc
                .perform(MockMvcRequestBuilders.get("/employee/age?age=" + age)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedJSONString));
    }

    @Test
    @DisplayName("When getEmployeesByPosition() is executed, then return employees with given position")
    public void testGetEmployeesByPosition() throws Exception {
        String position = "Jr. Software Engineer";
        Mockito
                .when(employeeService.findByPosition(position))
                .thenReturn(employeeList.stream().filter(employee -> employee.getPosition().equals(position)).toList());

        String expectedJSONString = JSONArray.toJSONString(List.of(aliDTO, rDTO, ravenDTO));
        mockMvc
                .perform(MockMvcRequestBuilders.get("/employee/position?position=" + position)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedJSONString));
    }

    @Test
    @DisplayName("When getEmployeeWithHighestSalary() is executed, then return employee with highest salary")
    public void testGetEmployeeWithHighestSalary() throws Exception {
        Mockito
                .when(employeeService.findTopByOrderBySalaryDesc())
                .thenReturn(employeeList.stream().max(Comparator.comparing(EmployeeDTO::getSalary)).get());

        ObjectMapper mapper = new ObjectMapper();
        String expectedJSONString = mapper.writeValueAsString(dondonDTO);
        mockMvc
                .perform(MockMvcRequestBuilders.get("/employee/salary/top")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedJSONString));
    }

}
