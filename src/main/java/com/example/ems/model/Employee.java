package com.example.ems.model;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="employee_tbl")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee implements Serializable {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public void setEmergencyContactNumber(String emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }

    public String getEmploymentCode() {
        return employmentCode;
    }

    public void setEmploymentCode(String employmentCode) {
        this.employmentCode = employmentCode;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getReportingManagerMail() {
        return reportingManagerMail;
    }

    public void setReportingManagerMail(String reportingManagerMail) {
        this.reportingManagerMail = reportingManagerMail;
    }

    public String getHrName() {
        return hrName;
    }

    public void setHrName(String hrName) {
        this.hrName = hrName;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public LocalDate getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(LocalDate projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public LocalDate getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(LocalDate projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPanCard() {
        return panCard;
    }

    public void setPanCard(String panCard) {
        this.panCard = panCard;
    }

    public String getAadharCard() {
        return aadharCard;
    }

    public void setAadharCard(String aadharCard) {
        this.aadharCard = aadharCard;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getCtcBreakUp() {
        return ctcBreakUp;
    }

    public void setCtcBreakUp(String ctcBreakUp) {
        this.ctcBreakUp = ctcBreakUp;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Personal details
    @NotBlank(message = "Full Name is mandatory")
    @Column(name = "FullName")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "Date_of_Birth")
    private LocalDate dob;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "Age")
    private int age;

    @NotBlank(message = "Current Address is mandatory")
    @Column(name = "Current_Address")
    private String currentAddress;

    @NotBlank(message = "Permanent Address is mandatory")
    @Column(name = "Permanent_Address")
    private String permanentAddress;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    @Column(name = "Phone_Number")
    private String phoneNumber;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Personal Mail is mandatory")
    @Column(name = "Personal_Mail")
    private String email;

    @Column(name = "Emergency_Contact_Name")
    private String emergencyContactName;

    @Pattern(regexp = "\\d{10}", message = "Emergency Contact Number must be 10 digits")
    @Column(name = "Emergency_Contact_Number")
    private String emergencyContactNumber;

    // Professional details
    @Pattern(regexp = "\\d{6}", message = "Employment code must be 6 digits")
    @Column(name = "Employment_Code")
    private String employmentCode;

    @NotBlank(message = "Role is mandatory")
    @Column(name = "Role")
    private String role;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Company Mail is mandatory")
    @Column(name = "Company_Mail")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Column(name = "Password")
    private String password;

    @Pattern(regexp = "\\d{8,12}", message = "Office phone must be between 8 to 12 digits")
    @Column(name = "Office_Phone")
    private String officePhone;

    @NotBlank(message = "City is mandatory")
    @Column(name = "City")
    private String city;

    @NotBlank(message = "Office Address is mandatory")
    @Column(name = "Office_Address")
    private String officeAddress;

    @Email(message = "Reporting Manager email should be valid")
    @NotBlank(message = "Reporting Manager Mail is mandatory")
    @Column(name = "Reporting_Manager_Mail")
    private String reportingManagerMail;

    @NotBlank(message = "HR Name is mandatory")
    @Column(name = "HR_Name")
    private String hrName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "Date_of_Joining")
    private LocalDate dateOfJoining;

    // Project details
    @Column(name = "Project_Code")
    private String projectCode;

    @Column(name = "Project_Start_Date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate projectStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "Project_End_Date")
    private LocalDate projectEndDate;

    @Column(name = "Project_Name")
    private String projectName;

    // Finance details
    @NotBlank(message = "PAN Card is mandatory")
    @Column(name = "PAN_Card")
    private String panCard;
    @NotBlank(message = "Aadhar Card is mandatory")
    @Column(name = "Aadhar_Card")
    private String aadharCard;

    @NotBlank(message = "Bank Name is mandatory")
    @Column(name = "Bank_Name")
    private String bankName;

    @NotBlank(message = "Branch Name is mandatory")
    @Column(name = "Branch_Name")
    private String branchName;

    @Pattern(regexp = "\\d{9,18}", message = "Bank Account Number must be between 9 to 18 digits")
    @Column(name = "Bank_Account_Number")
    private String bankAccountNumber;

    @NotBlank(message = "IFSC Code is mandatory")
    @Column(name = "IFSC")
    private String ifsc;

    @NotBlank(message = "CTC Breakup is mandatory")
    @Column(name = "CTC_BreakUp")
    private String ctcBreakUp;

}
