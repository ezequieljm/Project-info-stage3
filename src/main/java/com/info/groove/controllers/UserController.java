package com.info.groove.controllers;

import com.info.groove.dto.UserEntityDTO;
import com.info.groove.entity.UserEntity;
import com.info.groove.exceptions.UserNotFoundException;
import com.info.groove.mapper.UserEntityMapper;
import com.info.groove.service.IUserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
@RequestMapping(value = UserController.BASIS)
public class UserController {
    
    public static final String BASIS = "/user";

    @Autowired
    private IUserEntityService userEntityService;

    @GetMapping(value = "/")
    public String helloUser(){ return "Hello user"; }

    @GetMapping(value = "/fullname/{firstname}/{lastname}")
    public ResponseEntity<Map<String,Object>> getUserWithFullname(
            @PathVariable String firstname,
            @PathVariable String lastname
    ){
        Map<String,Object> response = new HashMap<String,Object>();
        UserEntity user = userEntityService.searchByFullname(firstname, lastname);

        response.put(
                String.format("User with fullname: %s %s",firstname, lastname),
                UserEntityMapper.entityToDto(user)
        );

        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }

    @GetMapping(value = "/{dni}")
    public ResponseEntity<Map<String,Object>> getUserWithDni(@PathVariable int dni){

        Map<String,Object> response = new HashMap<String,Object>();
        Optional<UserEntity> user = userEntityService.searchByDni(dni);

        /*
        * In this case we return an exception within the controller because we're work with optional objects
        * First we ask is it's empty and then we solve
        * */
        if (user.isEmpty()) throw new UserNotFoundException(String.format("User with dni %s not exists", dni));

        UserEntity originalUser = user.get();

        response.put(
                String.format("User with dni %s",dni),
                UserEntityMapper.entityToDto(originalUser)
        );

        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Map<String,Object>> getAllUsers() {
        Map<String,Object> response = new HashMap<>();
        List<UserEntity> users = userEntityService.findAllUsers();
        response.put("All users", users);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/all-actives")
    public ResponseEntity<Map<String,Object>> getAllActiveUsers() {
        Map<String,Object> response = new HashMap<>();
        List<UserEntity> activeUsers = userEntityService
                .findAllUsers()
                .stream()
                .filter(u -> u.getUserStatus() == true)
                .collect(Collectors.toList());
        response.put("All active users", activeUsers);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Map<String,Object>> registerUser(@RequestBody @Valid UserEntityDTO userDto) {
        Map<String,Object> response = new HashMap<>();
        UserEntityDTO user = userEntityService.save(UserEntityMapper.dtoToEntity(userDto));

        response.put("Stored user: ", user);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);

    }

    @PutMapping(value = "/update/{key}")
    public ResponseEntity<Map<String,Object>> updateUser(
            @RequestBody @Valid UserEntityDTO userDto,
            @PathVariable String key
    ) {
        Map<String,Object> response = new HashMap<>();
        UserEntityDTO updatedUser = userEntityService.updateUserEntity(UserEntityMapper.dtoToEntity(userDto), key);

        response.put("Update user: ", updatedUser);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/{key}")
    public ResponseEntity<Map<String,Object>> deleteUser(@PathVariable Long id, @PathVariable String key) {
        Map<String,Object> response = new HashMap<>();
        userEntityService.deleteUserEntity(id,key);
        response.put(String.format("User with id %s deleted", id), null);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }

}
