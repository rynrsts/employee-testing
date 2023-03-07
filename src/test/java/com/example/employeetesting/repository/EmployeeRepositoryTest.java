package com.example.employeetesting.repository;

import com.example.employeetesting.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private List<Employee> employeeList;
    private Employee ali, dondon, michael, r, raven;


    @Before
    public void setup() {
        employeeList = employeeRepository.findAll();
        ali = employeeList.get(0);
        dondon = employeeList.get(1);
        michael = employeeList.get(2);
        r = employeeList.get(3);
        raven = employeeList.get(4);
    }

    @Test
    @DisplayName("When findAll() is executed, then return all employees")
    public void testFindAll_returnAllEmployees() {
        Assertions.assertThat(employeeList).isNotEmpty();
    }

    @Test
    @DisplayName("When findBySalaryGreaterThan() is executed, then return employees greater than given salary")
    public void testFindBySalaryGreaterThan() {
        double salary = 100000d;

        List<Employee> actualList = employeeRepository.findBySalaryGreaterThan(salary);

        Assertions.assertThat(actualList).containsExactly(dondon, michael);
    }

    @Test
    @DisplayName("When findByAgeGreaterThan() is executed, then return employees greater than given age")
    public void testFindByAgeGreaterThan() {
        int age = 24;

        List<Employee> actualList = employeeRepository.findByAgeGreaterThan(age);

        Assertions.assertThat(actualList).containsExactly(ali, dondon);
    }

    @Test
    @DisplayName("When findByPosition() is executed, then return employees with given position")
    public void testFindByPosition() {
        String position = "Jr. Software Engineer";

        List<Employee> actualList = employeeRepository.findByPosition(position);

        Assertions.assertThat(actualList).containsExactly(ali, r, raven);
    }

    @Test
    @DisplayName("When findTopByOrderBySalaryDesc() is executed, then return employee with highest salary")
    public void testFindTopByOrderBySalaryDesc_returnEmployeeWithHighestSalary() {
        Employee actualObject = employeeRepository.findTopByOrderBySalaryDesc();

        Assertions.assertThat(actualObject).isEqualTo(dondon);
    }

}
