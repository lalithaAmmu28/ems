package com.example.ems.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ems.model.Employee;
import java.time.LocalDate;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{
    Optional<Employee> findEmployeeById(Long id);
    Optional<Employee> findByUsername(String username);
    Optional<Employee> findByUsernameAndPhoneNumberAndDob(String username, String phoneNumber, LocalDate dob);    
}