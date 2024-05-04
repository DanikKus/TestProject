package kz.nish.springapp.nishApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, max = 100, message = "Title should be between 2 and 100")
    private String title;

    @Column(name = "place")
    @NotEmpty(message = "Place should not be empty")
    @Size(min = 2, max = 100, message = "Place should be between 2 and 100")
    private String place;

    @Column(name = "startdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp startdate;

    @Column(name = "finishdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp finishdate;

    @Column(name = "time_created")
    private Timestamp time_created;

    public Event() {

    }

    public Event(String title, String place, Timestamp startdate, Timestamp finishdate, Timestamp time_created) {
        this.title = title;
        this.place = place;
        this.startdate = startdate;
        this.finishdate = finishdate;
        this.time_created = time_created;
    }

    public int getId() {
        return id;}

    public void setId(int id) {
        this.id = id;
    }

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
