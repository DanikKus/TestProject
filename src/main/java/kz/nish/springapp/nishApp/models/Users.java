package kz.nish.springapp.nishApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class Users {


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
    public String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    public Role role;


    @Column(name = "isenabled")
    public boolean isenabled;

    public Users() {

    }

    public Users(String name, String surname, String fathername, String educationClass, String iin, String email, String password, Role role, boolean isenabled) {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isIsenabled() {
        return isenabled;
    }

    public void setIsenabled(boolean isenabled) {
        this.isenabled = isenabled;
    }
}
