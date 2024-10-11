let currentStep = 0;

// Function to show the current step of the form
function showStep(step) {
    const steps = document.querySelectorAll(".step");
    steps.forEach((s, i) => {
        s.style.display = i === step ? "block" : "none"; // Show the current step and hide others
    });
}

// Function to handle moving to the next step
function nextStep() {
    const fieldsToValidate = [
        // Step 1 fields
        ['fullName', 'dob', 'age', 'mobile', 'email', 'emergencyContactName', 'emergencyContactNumber'],
        // Step 2 fields
        ['employmentCode', 'companyMail', 'pass', 'officePhone', 'city', 'officeAddress', 'R_ManagerMail', 'HrName', 'dateOfJoining'],
        // Step 3 fields
        ['projectCode', 'startDate', 'endDate', 'projectName'],
        // Step 4 fields
        ['panCard', 'aadharCard', 'bankName', 'bankAccNum', 'branchName', 'ifsc', 'ctcBreakUp']
    ];

    let allValid = true; // Track validity of fields
    const currentFields = fieldsToValidate[currentStep]; // Get fields for the current step

    currentFields.forEach(fieldId => {
        const field = document.getElementById(fieldId);
        if (field && !validateField(field)) { // Validate each field
            allValid = false; // If any field is invalid
        }
    });

    if (allValid) {
        currentStep++; // Move to the next step
        if (currentStep < fieldsToValidate.length) {
            showStep(currentStep); // Show the new step
        } else {
            alert('All steps completed!'); // Handle form submission if needed
            // document.getElementById('employeeForm').submit(); // Uncomment this line to submit the form
        }
    }
}

// Function to handle moving to the previous step
function prevStep() {
    if (currentStep > 0) {
        currentStep--; // Decrement the step
        showStep(currentStep); // Show the previous step
    }
}

// Function to calculate and fill age based on the Date of Birth
function calculateAndFillAge() {
    const dobField = document.getElementById('dob');
    const ageField = document.getElementById('age');

    if (dobField.value.trim() !== '') {
        const dob = new Date(dobField.value);
        const currentYear = new Date().getFullYear();
        const birthYear = dob.getFullYear();
        const calculatedAge = currentYear - birthYear;

        // Fill the age field with the calculated age
        ageField.value = calculatedAge;
    } else {
        // Clear age field if DOB is empty
        ageField.value = '';
    }
}

// Function to validate individual fields
function validateField(field) {
    let isValid = true; // Assume field is valid initially
    const errorMessageElement = document.getElementById(`${field.id}-error`);
    errorMessageElement.textContent = ''; // Clear previous error message

    switch (field.id) {
        case 'fullName':
            if (field.value.trim().length === 0) {
                errorMessageElement.textContent = "'Full Name cannot be empty.'";
                isValid = false;
            }
            break;

        case 'dob':
            const dob = new Date(field.value);
            const age = parseInt(document.getElementById('age').value);
            const currentYear = new Date().getFullYear();
            const birthYear = dob.getFullYear();
            if (currentYear - birthYear !== age) {
                errorMessageElement.textContent = "'Age does not match the Date of Birth.'";
                isValid = false;
            }
            break;

        case 'age':
            const dobValue = document.getElementById('dob').value;
            if (dobValue) {
                const dob = new Date(dobValue);
                const calculatedAge = new Date().getFullYear() - dob.getFullYear();
                if (parseInt(field.value) !== calculatedAge) {
                    errorMessageElement.textContent = "'Age must match the calculated age based on Date of Birth.'";
                    isValid = false;
                }
            }
            break;
            case 'mobile':
                const phonePattern = /^\d{10}$/;
                if (!phonePattern.test(field.value.trim())) {
                    errorMessageElement.textContent = "'Phone Number must be a 10-digit number.'";
                    isValid = false;
                }
                break;
    
            case 'email':
                if (!field.value.includes('@')) {
                    errorMessageElement.textContent = "'Email must be a valid format.'";
                    isValid = false;
                }
                break;
    
            case 'emergencyContactName':
                if (field.value.trim().length === 0) {
                    isValid = true;
                }
                break;
    
            case 'emergencyContactNumber':
                const emergencyContactPattern = /^\d{10}$/;
                if (!emergencyContactPattern.test(field.value.trim())) {
                    errorMessageElement.textContent = "'Emergency Contact Number must be a 10-digit number.'";
                    isValid = false;
                }
                if(field.value.trim().length === 0){
                    isValid = true;
                }
                break;
    
            case 'employmentCode':
                const employmentCodePattern = /^\d{6}$/;
                if (!employmentCodePattern.test(field.value.trim())) {
                    errorMessageElement.textContent = "'Employment Code must be a 6-digit number.'";
                    isValid = false;
                }
                break;
    
            case 'companyMail':
                if (!field.value.includes('@')) {
                    errorMessageElement.textContent = "'Company Mail must be a valid email format.'";
                    isValid = false;
                }
                break;
    
            case 'officePhone':
                const officePhonePattern = /^\d{8,12}$/;
                if (!officePhonePattern.test(field.value.trim())) {
                    errorMessageElement.textContent = "'Office Phone must be between 8 to 12 digits.'";
                    isValid = false;
                }
                break;
    
            case 'projectCode':
                const projectCodePattern = /^\d{6}$/; 
                if (field.value.trim().length === 0) {
                    isValid = true;
                }
                if (!projectCodePattern.test(field.value.trim())) {
                    errorMessageElement.textContent = "'Project Code must be a 6-digit number.'";
                    isValid = false;
                }
                break;
    
            case 'panCard':
                const panCardPattern = /[A-Z]{5}[0-9]{4}[A-Z]{1}/; // Example: ABCDE1234F
                if (!panCardPattern.test(field.value.trim())) {
                    errorMessageElement.textContent = "'PAN Card must be in the format: ABCDE1234F'";
                    isValid = false;
                }
                break;
    
            case 'aadharCard':
                const aadharCardPattern = /^\d{12}$/; // 12 digits
                if (!aadharCardPattern.test(field.value.trim())) {
                    errorMessageElement.textContent = "'Aadhar Card must be a 12-digit number.'";
                    isValid = false;
                }
                break;
    
            case 'bankAccNum':
                const bankAccountNumberPattern = /^\d{9,18}$/; // 9 to 18 digits
                if (!bankAccountNumberPattern.test(field.value.trim())) {
                    errorMessageElement.textContent = "'Bank Account Number must be between 9 to 18 digits.'";
                    isValid = false;
                }
                break;
    
            case 'ifsc':
                const ifscPattern = /^[A-Z]{4}0[A-Z0-9]{6}$/; // Example: ABCD0123456
                if (!ifscPattern.test(field.value.trim())) {
                    errorMessageElement.textContent = "'IFSC Code must be in the format: ABCD0123456'";
                    isValid = false;
                }
                break;
    
            case 'ctcBreakUp':
                const ctcBreakUpPattern = /^\d+(\.\d{1,2})?$/; // Positive decimal number
                if (!ctcBreakUpPattern.test(field.value.trim())) {
                    errorMessageElement.textContent = "'CTC BreakUp must be a positive number, optionally with two decimal places.'";
                    isValid = false;
                }
                break;
    
            default:
                break;
        }
    
        if (!isValid) {
            field.focus(); // Keep focus on the invalid field
        }
    
        return isValid; // Return the validity of the field
}
    // Hook the calculateAndFillAge function to the change event for the DOB field
document.getElementById('dob').addEventListener('change', calculateAndFillAge);

// Hook the validateField function to the blur event for each input field
const fields = [
    'fullName', 'dob', 'age', 'mobile', 'email',
    'emergencyContactName', 'emergencyContactNumber',
    'employmentCode', 'companyMail', 'officePhone',
    'projectCode', 'panCard', 'aadharCard',
    'bankAccNum', 'ifsc', 'ctcBreakUp'
];

fields.forEach(fieldId => {
    const field = document.getElementById(fieldId);
    if (field) {
        // Validate the field on blur
        field.addEventListener('blur', function() {
            validateField(field);
        });

        // Clear error message on input
        field.addEventListener('input', function() {
            const errorMessageElement = document.getElementById(`${field.id}-error`);
            errorMessageElement.textContent = ''; // Clear the error message
        });
    }
});

// Hook the validateForm function to the form submission if needed
// document.getElementById('employeeForm').addEventListener('submit', validateForm);

// Initialize the form by showing the first step
showStep(currentStep);