package com.example.ems.controller;

import com.example.ems.model.Employee;
import com.example.ems.service.EmployeeService;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Display personal details of the logged-in employee
    @GetMapping("/personal-details")
    public String showPersonalDetails(Authentication authentication, Model model) {
        String username = ((User) authentication.getPrincipal()).getUsername();
        Employee employee = employeeService.findEmployeeByUsername(username);
        model.addAttribute("employee", employee);
        return "personal-details"; // Returns personal-details.html
    }

    // Display professional details of the logged-in employee
    @GetMapping("/professional-details")
    public String showProfessionalDetails(Authentication authentication, Model model) {
        String username = ((User) authentication.getPrincipal()).getUsername();
        Employee employee = employeeService.findEmployeeByUsername(username);
        model.addAttribute("employee", employee);
        return "professional-details"; // Returns professional-details.html
    }

    // Display project details of the logged-in employee
    @GetMapping("/project-details")
    public String showProjectDetails(Authentication authentication, Model model) {
        String username = ((User) authentication.getPrincipal()).getUsername();
        Employee employee = employeeService.findEmployeeByUsername(username);
        model.addAttribute("employee", employee);
        return "project-details"; // Returns project-details.html
    }

    // Display finance details of the logged-in employee
    @GetMapping("/finance-details")
    public String showFinanceDetails(Authentication authentication, Model model) {
        String username = ((User) authentication.getPrincipal()).getUsername();
        Employee employee = employeeService.findEmployeeByUsername(username);
        model.addAttribute("employee", employee);
        return "finance-details"; // Returns finance-details.html
    }

    @GetMapping("/download-payslip")
    public ResponseEntity<InputStreamResource> downloadPayslip(Authentication authentication) throws IOException, DocumentException {
        // Retrieve the username of the logged-in employee
        String username = ((User) authentication.getPrincipal()).getUsername();
        Employee employee = employeeService.findEmployeeByUsername(username);

        // Generate PDF with finance details
        ByteArrayInputStream pdfStream = generatePayslipPdf(employee);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=payslip.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdfStream));
    }

    private ByteArrayInputStream generatePayslipPdf(Employee employee) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();
    
        try {
            PdfWriter.getInstance(document, out); // Catch DocumentException here
            document.open();
    
            document.add(new Paragraph("Employee Payslip"));
            document.add(new Paragraph("Name: " + employee.getName()));
            document.add(new Paragraph("PAN Card: " + employee.getPanCard()));
            document.add(new Paragraph("Aadhar Card: " + employee.getAadharCard()));
            document.add(new Paragraph("Bank Name: " + employee.getBankName()));
            document.add(new Paragraph("Bank Account Number: " + employee.getBankAccountNumber()));
            document.add(new Paragraph("Branch Name: " + employee.getBranchName()));
            document.add(new Paragraph("IFSC Code: " + employee.getIfsc()));
            document.add(new Paragraph("CTC BreakUp: " + employee.getCtcBreakUp()));
    
            document.close();
        } catch (DocumentException ex) {
            ex.printStackTrace(); // Log the exception for debugging
        }
    
        return new ByteArrayInputStream(out.toByteArray());
    }
    
}
