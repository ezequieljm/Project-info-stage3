package com.info.groove.controllers;

import com.info.groove.dto.UserEntityDTO;
import com.info.groove.entity.UserEntity;
import com.info.groove.service.users.IUserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = UserController.BASIS)
public class UserController {
    
    public static final String BASIS = "/user";

    @Autowired
    private IUserEntityService userEntityService;

    @GetMapping
    public String helloUser(){ return "Hello user"; }


    @GetMapping(value = "/all")
    public ResponseEntity<Map<String,Object>> getAllUsers() {
        Map<String,Object> response = new HashMap<>();
        List<UserEntity> users = userEntityService.searchAllUsers();
        response.put("All users", users);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/all-actives")
    public ResponseEntity<Map<String,Object>> getAllActiveUsers() {
        Map<String,Object> response = new HashMap<>();
        List<UserEntity> activeUsers = userEntityService.searchAllActiveUsers();
        response.put("All active users", activeUsers);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/fullname/{firstname}/{lastname}")
    public ResponseEntity<Map<String,Object>> getUserWithFullname(
            @PathVariable String firstname,
            @PathVariable String lastname
    ){
        Map<String,Object> response = new HashMap<String,Object>();
        UserEntityDTO userDto = userEntityService.searchByFullname(firstname, lastname);
        response.put( String.format("User with fullname: %s %s",firstname, lastname),userDto);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }

    @GetMapping(value = "/{dni}")
    public ResponseEntity<Map<String,Object>> getUserWithDni(
            @PathVariable int dni
    ){
        Map<String,Object> response = new HashMap<String,Object>();
        UserEntityDTO userDto = userEntityService.searchByDni(dni);
        response.put( String.format("User with dni %s",dni),userDto);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }


    @PostMapping(value = "/register")
    public ResponseEntity<Map<String,Object>> registerUser(
            @RequestBody @Valid UserEntityDTO userDto
    ) {
        Map<String,Object> response = new HashMap<>();
        UserEntityDTO user = userEntityService.save(userDto);
        response.put("Stored user: ", user);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Map<String,Object>> updateUser(
            @RequestBody @Valid UserEntityDTO userDto
    ) {
        Map<String,Object> response = new HashMap<>();
        UserEntityDTO updatedUser = userEntityService.updateUserEntity(userDto);
        response.put("Update user: ", updatedUser);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }



    // Logical deletion
    @PutMapping(value = "/delete")
    public ResponseEntity<Map<String,Object>> deleteUser(
            @RequestBody @Valid UserEntityDTO userDto
    ) {
        Map<String,Object> response = new HashMap<>();
        UserEntityDTO user = userEntityService.logicalDeletionUserEntity(userDto);
        response.put(String.format("User with id %s deleted", user.getUserId()), user);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Map<String,Object>> permanentDeletionUser(
            @RequestBody @Valid UserEntityDTO userDto
    ) {
        Map<String,Object> response = new HashMap<>();
        userEntityService.deleteUserEntity(userDto);
        response.put(String.format("User with id %s deleted", userDto.getUserId()), null);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

}
