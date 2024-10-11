package com.example.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ems.model.User;
import com.example.ems.exception.EmployeeNotFoundException;
import com.example.ems.model.Employee;
import java.time.LocalDate;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.ems.repository.EmployeeRepository;
import com.example.ems.repository.UserRepository;
import java.time.format.DateTimeParseException;

@Service
public class EmployeeService{
public EmployeeService(EmployeeRepository employeeRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
    this.employeeRepository = employeeRepository;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
}

    @Autowired
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee){
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        Employee savedEmployee = employeeRepository.save(employee);
        User user = new User();
        user.setUsername(employee.getUsername());
        user.setPassword(employee.getPassword());
        user.setRole(employee.getRole());
        userRepository.save(user);
        return savedEmployee;

    }
    public List<Employee> findAllEmployees(){
        return employeeRepository.findAll();
    }
    public Employee findEmployeeById(Long id){
        return employeeRepository.findEmployeeById(id).orElseThrow(()->new EmployeeNotFoundException("employee by id "+id+"was not found"));
    }
    public Employee updateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    public void deleteEmployee(Long id) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee by id " + id + " was not found"));
    
        // Delete the employee record
        employeeRepository.delete(existingEmployee);
    
        // Find and delete the corresponding user record
        userRepository.findByUsername(existingEmployee.getUsername()).ifPresent(user -> {
            userRepository.delete(user);  // Delete the user if found
        });
    }
    
    public Employee findEmployeeByUsername(String username) {
        return employeeRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Employee not found with username: " + username));
    }
    public Employee validateEmployeeDetails(String username, String phoneNumber, String dob) {
        LocalDate dobParsed;
        try {
            dobParsed = LocalDate.parse(dob);  // Parse the string to LocalDate
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format for DOB. Expected format: YYYY-MM-DD");
        }

        // Pass the parsed LocalDate to the repository method
        return employeeRepository.findByUsernameAndPhoneNumberAndDob(username, phoneNumber, dobParsed)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee details not found"));
    }

    // Update the employee's password in the database
    public void updatePassword(String username, String newPassword) {
        Employee employee = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

        String encodedPassword = passwordEncoder.encode(newPassword);
        employee.setPassword(encodedPassword);
        employeeRepository.save(employee);

        // Also update the User entity if it's separate from Employee
        userRepository.findByUsername(username).ifPresent(user -> {
            user.setPassword(encodedPassword);
            userRepository.save(user);
        });
    }
    }
    

