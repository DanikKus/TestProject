package kz.nish.springapp.nishApp.controllers;

import jakarta.validation.Valid;
import kz.nish.springapp.nishApp.dto.EventDTO;
import kz.nish.springapp.nishApp.models.Event;
import kz.nish.springapp.nishApp.services.EventsService;
import kz.nish.springapp.nishApp.util.EventErrorResponse;
import kz.nish.springapp.nishApp.util.EventNotCreatedException;
import kz.nish.springapp.nishApp.util.EventNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events")
public class EventController {

    public final EventsService eventsService;

    public final ModelMapper modelMapper;

    @Autowired
    public EventController(EventsService eventsService, ModelMapper modelMapper) {
        this.eventsService = eventsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<EventDTO> getEvents() {

        return eventsService.findAll().stream().map(this::convertToEventDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EventDTO getEvent(@PathVariable("id") int id) {

        return convertToEventDTO(eventsService.findOne(id));
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid EventDTO eventDTO, BindingResult bindingResult) {
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

        eventsService.save(convertToEvent(eventDTO));

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

    private Event convertToEvent(EventDTO eventDTO){
        return modelMapper.map(eventDTO, Event.class);
    }

    private EventDTO convertToEventDTO(Event event){
        return modelMapper.map(event, EventDTO.class);
    }


}