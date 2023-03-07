package com.example.employeetesting.service;

import com.example.employeetesting.dto.EmployeeDTO;
import com.example.employeetesting.mapper.EmployeeMapper;
import com.example.employeetesting.model.Employee;
import com.example.employeetesting.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Comparator;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeService employeeService = new EmployeeServiceImpl();


    private EmployeeDTO aliDTO, dondonDTO, michaelDTO, rDTO, ravenDTO;
    private List<Employee> employeeList;


    @BeforeEach
    public void setup() {
        Employee ali = Employee
                .builder()
                .name("Ali")
                .age(25)
                .salary(40000d)
                .position("Jr. Software Engineer")
                .build();
        Employee dondon = Employee
                .builder()
                .name("Dondon")
                .age(26)
                .salary(200000d)
                .position("Senior Software Engineer")
                .build();
        Employee michael = Employee
                .builder()
                .name("Michael")
                .age(22)
                .salary(110000d)
                .position("Software Engineer")
                .build();
        Employee r = Employee
                .builder()
                .name("R")
                .age(23)
                .salary(70000d)
                .position("Jr. Software Engineer")
                .build();
        Employee raven = Employee
                .builder()
                .name("Raven")
                .age(23)
                .salary(60000d)
                .position("Jr. Software Engineer")
                .build();
        employeeList = List.of(ali, dondon, michael, r, raven);

        aliDTO = employeeMapper.modelToDto(ali);
        dondonDTO = employeeMapper.modelToDto(dondon);
        michaelDTO = employeeMapper.modelToDto(michael);
        rDTO = employeeMapper.modelToDto(r);
        ravenDTO = employeeMapper.modelToDto(raven);
    }

    @Test
    @DisplayName("When findBySalaryGreaterThan() is executed, then return employees greater than given salary")
    public void testFindBySalaryGreaterThan() {
        double salary = 100000d;
        Mockito
                .when(employeeRepository.findBySalaryGreaterThan(salary))
                .thenReturn(employeeList.stream().filter(employee -> employee.getSalary() > salary).toList());

        List<EmployeeDTO> actualList = employeeService.findBySalaryGreaterThan(salary);

        Assertions.assertThat(actualList).containsExactly(dondonDTO, michaelDTO);
    }

    @Test
    @DisplayName("When findByAgeGreaterThan() is executed, then return employees greater than given age")
    public void testFindByAgeGreaterThan() {
        int age = 24;
        Mockito
                .when(employeeRepository.findByAgeGreaterThan(age))
                .thenReturn(employeeList.stream().filter(employee -> employee.getAge() > age).toList());

        List<EmployeeDTO> actualList = employeeService.findByAgeGreaterThan(age);

        Assertions.assertThat(actualList).containsExactly(aliDTO, dondonDTO);
    }

    @Test
    @DisplayName("When findByPosition() is executed, then return employees with given position")
    public void testFindByPosition() {
        String position = "Jr. Software Engineer";
        Mockito
                .when(employeeRepository.findByPosition(position))
                .thenReturn(employeeList.stream().filter(employee -> employee.getPosition().equals(position)).toList());

        List<EmployeeDTO> actualList = employeeService.findByPosition(position);

        Assertions.assertThat(actualList).containsExactly(aliDTO, rDTO, ravenDTO);
    }

    @Test
    @DisplayName("When findTopByOrderBySalaryDesc() is executed, then return employees with highest salary")
    public void testFindTopByOrderBySalaryDesc_returnEmployeesWithHighestPosition() {
        Mockito
                .when(employeeRepository.findTopByOrderBySalaryDesc())
                .thenReturn(employeeList.stream().max(Comparator.comparing(Employee::getSalary)).get());

        EmployeeDTO actualEmployee = employeeService.findTopByOrderBySalaryDesc();

        Assertions.assertThat(actualEmployee).isEqualTo(dondonDTO);
    }

}
