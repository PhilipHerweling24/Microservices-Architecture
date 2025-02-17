package com.a00326153.library.controller;

import com.a00326153.library.dto.UserDto;
import com.a00326153.library.entity.User;
import com.a00326153.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userSerivce;


    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody User user){
        return ResponseEntity.ok(userSerivce.createUser(user));
    }

    @GetMapping
    public ResponseEntity<Page<UserDto>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(userSerivce.getAllUsers(pageable));
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userSerivce.getUserById(id));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userSerivce.updateUser(id, userDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userSerivce.deleteUser(id);
        return ResponseEntity.ok("Successfully deleted user with id: "+id);}

    }
