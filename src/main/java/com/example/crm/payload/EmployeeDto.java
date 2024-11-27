package com.example.crm.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class EmployeeDto {
    private Long id;

    @NotBlank
    @Size(min = 3, message = "At least 3 characters are required")
    private String name;
    @Email
    private String emailId;
 @Size(min = 10, max = 10, message = "Mobile number should be between 10 and 15 digits")
    private String mobile;
}
