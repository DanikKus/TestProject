package kz.nish.springapp.nishApp.controllers;


import jakarta.validation.Valid;
import kz.nish.springapp.nishApp.dto.UserDTO;
import kz.nish.springapp.nishApp.models.Users;
import kz.nish.springapp.nishApp.services.UsersService;
import kz.nish.springapp.nishApp.util.*;
import org.apache.catalina.User;
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
@RequestMapping("/users")
public class UsersController {

    private final ModelMapper modelMapper;
    public final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService, ModelMapper modelMapper) {
        this.usersService = usersService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<UserDTO> getUsers(){
        return usersService.findALL().stream().map(this::convertToUserDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable("id") int id){
        return convertToUserDTO(usersService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }

            throw new UserNotCreatedException(errorMsg.toString());
        }

        usersService.save(convertToUser(userDTO));

        return ResponseEntity.ok(HttpStatus.CREATED);


    }



    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(EventNotFoundException e){
        UserErrorResponse response = new UserErrorResponse(
                "Event with this id wasn't found",
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotCreatedException e){
        UserErrorResponse response = new UserErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Users convertToUser(UserDTO userDTO) {

        return modelMapper.map(userDTO, Users.class);

    }

    private UserDTO convertToUserDTO(Users users){
        return modelMapper.map(users, UserDTO.class);
    }


}
