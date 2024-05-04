package kz.nish.springapp.nishApp.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDTO {

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100")
    public String name;

    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 2, max = 100, message = "Surname should be between 2 and 100")
    public String surname;

    @NotEmpty(message = "Fathername should not be empty")
    @Size(min = 2, max = 100, message = "Fathername should be between 2 and 100")
    public String fathername;

    @NotEmpty(message = "Class should not be empty")
    public String educationClass;

    @NotEmpty(message = "IIN should not be empty")
    @Size(max = 12, message = "IIN should be 12 characters")
    public String iin;

    @NotEmpty(message = "Email should not be empty")
    @Email
    public String email;

    @NotEmpty(message = "role should not be empty")
    @Size(min = 2, max = 100, message = "role should be between 8 and 20")
    public String role;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getEducationClass() {
        return educationClass;
    }

    public void setEducationClass(String educationClass) {
        this.educationClass = educationClass;
    }

    public String getIin() {
        return iin;
    }

    public void setIin(String iin) {
        this.iin = iin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
