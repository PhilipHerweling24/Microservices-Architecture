package com.a00326153.library.controller;

import com.a00326153.library.constants.ControllerConstants;
import com.a00326153.library.dto.ResponseDto;
import com.a00326153.library.dto.UserDto;
import com.a00326153.library.entity.User;
import com.a00326153.library.serviceImpl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userSerivce;


    //Creates a User
    @PostMapping
    public ResponseEntity<ResponseDto> createUser(@Valid @RequestBody User user){
        userSerivce.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(ControllerConstants.STATUS_201,
                ControllerConstants.MESSAGE_201_USER_CREATE));
    }

    //Gets all the Users
    @GetMapping
    public ResponseEntity<Page<UserDto>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(userSerivce.getAllUsers(pageable));
    }

    //Gets a User by there ID
    @GetMapping("/byId/{id}")
    public ResponseEntity<UserDto> getUserById(@Valid@PathVariable Long id) {
        return ResponseEntity.ok(userSerivce.getUserById(id));
    }


    //Updates a User, using there User ID
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto> updateUser(@Valid@PathVariable Long id, @Valid@RequestBody UserDto userDto) {
        userSerivce.updateUser(id, userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(ControllerConstants.STATUS_201,
                ControllerConstants.MESSAGE_201_USER_UPDATE));
    }

    //Deletes a USer, using there USer ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteUser(@Valid@PathVariable Long id) {
        userSerivce.deleteUser(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(ControllerConstants.STATUS_201,
                ControllerConstants.MESSAGE_201_USER_DELETE));
    }
}
