package com.redis.example.spring.redis.controller;

import com.redis.example.spring.redis.entity.UserEntity;
import com.redis.example.spring.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        Optional<UserEntity> user = userService.getUser(id);
        if (user.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(user.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("USER NOT FOUND");
    }

    @PostMapping("/user/")
    public ResponseEntity<?> addUser(@RequestBody UserEntity user){
        UserEntity userEntity = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
