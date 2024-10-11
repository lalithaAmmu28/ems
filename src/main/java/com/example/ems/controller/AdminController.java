package com.example.ems.controller;

import com.example.ems.model.Employee;
import com.example.ems.service.EmployeeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final EmployeeService employeeService;

    public AdminController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    } 
    // Display the form for creating a new employee
    @GetMapping("/employee/create")
    public String showCreateEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee()); // Empty employee object to be filled in the form
        return "create-employee"; // Return the create-employee Thymeleaf template
    }

    // Handle the submission of the create employee form
    @PostMapping("/employee/create")
    public String createEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.addEmployee(employee);
        return "redirect:/admin/dashboard"; // Redirect to admin dashboard after successful creation
    }

    // Display the form for updating an existing employee
    @GetMapping("/employee/update/{id}")
    public String showUpdateEmployeeForm(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.findEmployeeById(id);
        model.addAttribute("employee", employee); // Add the existing employee object to the model
        return "update-employee"; // Return the update-employee Thymeleaf template
    }

    // Handle the submission of the update employee form
    @PostMapping("/employee/update/{id}")
    public String updateEmployee(@PathVariable("id") Long id, @ModelAttribute("employee") Employee employee) {
        employee.setId(id); // Ensure the employee ID is set for the update
        employeeService.updateEmployee(employee);
        return "redirect:/admin/dashboard"; // Redirect to admin dashboard after successful update
    }

    // Handle the deletion of an employee
    @GetMapping("/employee/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/admin/dashboard"; // Redirect to admin dashboard after successful deletion
    }
    // Display the list of all employees in the admin dashboard
    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        List<Employee> employees = employeeService.findAllEmployees();
        model.addAttribute("employees", employees); // Add the employee list to the model
        return "admin-dashboard"; // Return the admin-dashboard Thymeleaf template
    }

    @GetMapping("/employee/{id}")
    public String viewEmployeeDetails(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.findEmployeeById(id);
        model.addAttribute("employee", employee);
        return "view-employee"; // Return the Thymeleaf template to display employee details
    }
    
    @GetMapping("/employee/employeeList") 
    public String employeeList(Model model) { 
        List<Employee> employees = employeeService.findAllEmployees(); 
        model.addAttribute("employees", employees);  
        return "employee-list"; 
}


}
