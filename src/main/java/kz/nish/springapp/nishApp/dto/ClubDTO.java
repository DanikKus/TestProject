package kz.nish.springapp.nishApp.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ClubDTO {

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, max = 100, message = "Title should be between 2 and 100")
    private String title;


    @NotEmpty(message = "Owner should not be empty")
    @Size(min = 2, max = 100, message = "Owner should be between 2 and 100")
    private String owner;


    @NotEmpty(message = "details should not be empty")
    @Size(min = 2, max = 300, message = "Details should be between 2 and 300")
    private String details;

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
