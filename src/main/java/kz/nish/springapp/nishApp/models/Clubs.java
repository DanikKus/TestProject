package kz.nish.springapp.nishApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "clubs")
public class Clubs {

    @Id
    @Column(name = "organization_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;


    @Column(name = "title")
    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, max = 100, message = "Title should be between 2 and 100")
    private String title;


    @Column(name = "owner")
    @NotEmpty(message = "Owner should not be empty")
    @Size(min = 2, max = 100, message = "Owner should be between 2 and 100")
    private String owner;


    @Column(name = "details")
    @NotEmpty(message = "details should not be empty")
    @Size(min = 2, max = 300, message = "Details should be between 2 and 300")
    private String details;


    public Clubs() {


    }

    public Clubs(String title, String owner, String details) {
        this.title = title;
        this.owner = owner;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
