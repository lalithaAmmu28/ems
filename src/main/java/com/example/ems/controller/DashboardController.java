package com.example.ems.controller;

import com.example.ems.model.Employee;
import com.example.ems.service.EmployeeService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DashboardController {

    private final EmployeeService employeeService;

    public DashboardController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("successMessage", "You have successfully logged in!");

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/admin/dashboard";
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_EMPLOYEE"))) {
            
            return "redirect:/employee/dashboard";
        }
        return "error";
    }

    @GetMapping("/employee/dashboard")
    public String employeeDashboard(Authentication authentication, Model model) {
        // Retrieve the username from the Authentication object
        String username = ((User) authentication.getPrincipal()).getUsername();
        
        // Use EmployeeService to find the employee by username
        Employee employee = employeeService.findEmployeeByUsername(username);
        
        // Add employee to the model so that Thymeleaf can access it
        model.addAttribute("employee", employee);
        
        return "employee-dashboard"; // Returns the employee-dashboard view
    }
}
