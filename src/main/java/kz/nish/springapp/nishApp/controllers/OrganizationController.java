package kz.nish.springapp.nishApp.controllers;


import jakarta.validation.Valid;
import kz.nish.springapp.nishApp.models.Event;
import kz.nish.springapp.nishApp.models.Organization;
import kz.nish.springapp.nishApp.services.OrganizationsService;
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
@RequestMapping("/organizations")
public class OrganizationController {

    public final OrganizationsService organizationsService;


    @Autowired
    public OrganizationController(OrganizationsService organizationsService) {
        this.organizationsService = organizationsService;
    }

    @GetMapping()
    public List<Organization> getOrganizations(){
        return organizationsService.findALL();
    }


    @GetMapping("/{id}")
    public Organization getOrganization(@PathVariable("id") int id){
        return organizationsService.findOne(id);
    }


    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Organization organization, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }

            throw new OrganizationNotCreatedException(errorMsg.toString());
        }

        organizationsService.save(organization);

        return ResponseEntity.ok(HttpStatus.OK);

    }

    @ExceptionHandler
    private ResponseEntity<OrganizationErrorResponse> handleException(OrganizationErrorResponse e){
        OrganizationErrorResponse response = new OrganizationErrorResponse(
                "Organization with this id wasn't found",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<OrganizationErrorResponse> handleException(OrganizationNotCreatedException e){
        OrganizationErrorResponse response = new OrganizationErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
