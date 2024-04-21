package kz.nish.springapp.nishApp.controllers;


import jakarta.validation.Valid;
import kz.nish.springapp.nishApp.models.Student;
import kz.nish.springapp.nishApp.services.StudentsService;
import kz.nish.springapp.nishApp.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    public final StudentsService studentsService;

    @Autowired
    public StudentController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @GetMapping()
    public List<Student> getStudents(){
        return studentsService.findALL();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id") int id){
        return studentsService.findOne(id);
    }

    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Student student, BindingResult bindingResult) {
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

        studentsService.save(student);

        return ResponseEntity.ok(HttpStatus.OK);

    }

    @ExceptionHandler
    private ResponseEntity<StudentErrorResponse> handleException(EventNotFoundException e){
        StudentErrorResponse response = new StudentErrorResponse(
                "Event with this id wasn't found",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<StudentErrorResponse> handleException(StudentNotCreatedException e){
        StudentErrorResponse response = new StudentErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



}
