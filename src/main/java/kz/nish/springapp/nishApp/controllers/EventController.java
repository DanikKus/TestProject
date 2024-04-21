package kz.nish.springapp.nishApp.controllers;

import jakarta.validation.Valid;
import kz.nish.springapp.nishApp.models.Event;
import kz.nish.springapp.nishApp.services.EventsService;
import kz.nish.springapp.nishApp.util.EventErrorResponse;
import kz.nish.springapp.nishApp.util.EventNotCreatedException;
import kz.nish.springapp.nishApp.util.EventNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    public final EventsService eventsService;

    @Autowired
    public EventController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @GetMapping()
    public List<Event> getEvents() {
        return eventsService.findAll();
    }

    @GetMapping("/{id}")
    public Event getEvent(@PathVariable("id") int id) {
        return eventsService.findOne(id);
    }


    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Event event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }

            throw new EventNotCreatedException(errorMsg.toString());
        }

        eventsService.save(event);

        return ResponseEntity.ok(HttpStatus.OK);

    }
    @ExceptionHandler
    private ResponseEntity<EventErrorResponse> handleException(EventNotFoundException e){
        EventErrorResponse response = new EventErrorResponse(
                "Event with this id wasn't found",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<EventErrorResponse> handleException(EventNotCreatedException e){
        EventErrorResponse response = new EventErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}