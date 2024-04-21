package kz.nish.springapp.nishApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "student")
public class Student {


    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100")
    public String name;

    @Column(name = "surname")
    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 2, max = 100, message = "Surname should be between 2 and 100")
    public String surname;

    @Column(name = "fathername")
    @NotEmpty(message = "Fathername should not be empty")
    @Size(min = 2, max = 100, message = "Fathername should be between 2 and 100")
    public String fathername;

    @Column(name = "class")
    @NotEmpty(message = "Class should not be empty")
    public String educationClass;


    @Column(name = "iin")
    @NotEmpty(message = "IIN should not be empty")
    @Size(max = 12, message = "IIN should be 12 characters")
    public String iin;

    @Column(name = "email")
    @NotEmpty(message = "Email should not be empty")
    @Email
    public String email;

    @Column(name = "password")
    @NotEmpty(message = "password should not be empty")
    @Size(min = 8, max = 20, message = "password should be between 8 and 20")
    public String password;

    @Column(name = "role")
    @NotEmpty(message = "role should not be empty")
    @Size(min = 2, max = 100, message = "role should be between 8 and 20")
    public String role;


    @Column(name = "isenabled")
    public boolean isenabled;

    public Student() {

    }

    public Student(String name, String surname, String fathername, String educationClass, String iin, String email, String password, String role, boolean isenabled) {
        this.name = name;
        this.surname = surname;
        this.fathername = fathername;
        this.educationClass = educationClass;
        this.iin = iin;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isenabled = isenabled;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isIsenabled() {
        return isenabled;
    }

    public void setIsenabled(boolean isenabled) {
        this.isenabled = isenabled;
    }
}
