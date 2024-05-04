package kz.nish.springapp.nishApp.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;

public class EventDTO {

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, max = 100, message = "Title should be between 2 and 100")
    private String title;

    @NotEmpty(message = "Place should not be empty")
    @Size(min = 2, max = 100, message = "Place should be between 2 and 100")
    private String place;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp startdate;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp finishdate;

    private Timestamp time_created;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Timestamp getStartdate() {
        return startdate;
    }

    public void setStartdate(Timestamp startdate) {
        this.startdate = startdate;
    }

    public Timestamp getFinishdate() {
        return finishdate;
    }

    public void setFinishdate(Timestamp finishdate) {
        this.finishdate = finishdate;
    }

    public Timestamp getTime_created() {
        return time_created;
    }

    public void setTime_created(Timestamp time_created) {
        this.time_created = time_created;
    }
}
