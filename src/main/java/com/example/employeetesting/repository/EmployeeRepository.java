package com.example.employeetesting.repository;

import com.example.employeetesting.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findBySalaryGreaterThan(double salary);

    List<Employee> findByAgeGreaterThan(int age);

    List<Employee> findByPosition(String position);

    Employee findTopByOrderBySalaryDesc();

}
