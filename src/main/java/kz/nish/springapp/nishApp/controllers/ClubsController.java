package kz.nish.springapp.nishApp.controllers;


import jakarta.validation.Valid;
import kz.nish.springapp.nishApp.dto.ClubDTO;
import kz.nish.springapp.nishApp.models.Clubs;
import kz.nish.springapp.nishApp.services.ClubsService;
import kz.nish.springapp.nishApp.util.*;
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
@RequestMapping("/clubs")
public class ClubsController {

    private final ModelMapper modelMapper;

    public final ClubsService clubsService;


    @Autowired
    public ClubsController(ClubsService clubsService, ModelMapper modelMapper) {
        this.clubsService = clubsService;
        this.modelMapper = modelMapper;

    }

    @GetMapping()
    public List<ClubDTO> getClubs(){
        return clubsService.findALL().stream().map(this::convertToClubDTO).collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public ClubDTO getClub(@PathVariable("id") int id){

        return convertToClubDTO(clubsService.findOne(id));
    }


    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid ClubDTO clubDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }

            throw new ClubsNotCreatedException(errorMsg.toString());
        }

        clubsService.save(convertToClub(clubDTO));

        return ResponseEntity.ok(HttpStatus.OK);

    }



    @ExceptionHandler
    private ResponseEntity<ClubsErrorResponse> handleException(ClubsErrorResponse e){
        ClubsErrorResponse response = new ClubsErrorResponse(
                "Clubs with this id wasn't found",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ClubsErrorResponse> handleException(ClubsNotCreatedException e){
        ClubsErrorResponse response = new ClubsErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Clubs convertToClub(ClubDTO clubDTO) {

        return modelMapper.map(clubDTO, Clubs.class);

    }


    private ClubDTO convertToClubDTO(Clubs clubs){

        return modelMapper.map(clubs, ClubDTO.class);

    }
}
