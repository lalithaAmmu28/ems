package com.example.ems.controller;

import com.example.ems.model.Employee;
import com.example.ems.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ForgotPasswordController {

    private final EmployeeService employeeService;

    public ForgotPasswordController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Show the forgot password form
    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgot-password";  // Return the forgot-password.html view
    }

    // Validate employee details (email, phone number, and DOB)
    @PostMapping("/forgot-password/validate")
public String validateUser(@RequestParam("email") String username,
                           @RequestParam("phoneNumber") String phoneNumber,
                           @RequestParam("dob") String dob,
                           Model model) {
    try {
        // Validate the employee by email, phone number, and date of birth
        Employee employee = employeeService.validateEmployeeDetails(username, phoneNumber, dob);

        if (employee != null) {
            // Store the validated employee's details to be used in the new password page
            model.addAttribute("employee", employee);
            return "redirect:/forgot-password/new-password?email=" + username; // Redirect to the new password page
        } else {
            model.addAttribute("errorMessage", "Details don't match our records.");
            return "forgot-password";
        }
    } catch (Exception e) {
        model.addAttribute("errorMessage", "Something went wrong. Please try again.");
        return "forgot-password";
    }
}


    // Show the new password form after successful validation
    @GetMapping("/forgot-password/new-password")
    public String showNewPasswordForm(@RequestParam("email") String username, Model model) {
        model.addAttribute("email", username);
        return "new-password";  // Return new-password.html view
    }

    // Update the employee password
    @PostMapping("/forgot-password/update-password")
    public String updatePassword(@RequestParam("email") String username,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmPassword") String confirmPassword,
                                 Model model) {

        // Check if the new passwords match
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("errorMessage", "Passwords do not match.");
            model.addAttribute("email", username);
            return "new-password";
        }

        try {
            // Update the employee's password
            employeeService.updatePassword(username, newPassword);
            model.addAttribute("successMessage", "Password updated successfully. Please login with your new password.");
            return "redirect:/login";  // Redirect to login page after success
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Could not update the password. Please try again.");
            model.addAttribute("email", username);
            return "new-password";
        }
    }
}
