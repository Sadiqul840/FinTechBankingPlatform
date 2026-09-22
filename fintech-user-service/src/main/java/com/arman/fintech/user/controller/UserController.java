package com.arman.fintech.user.controller;


import com.arman.fintech.user.dto.UserRequestDto;
import com.arman.fintech.user.dto.UserResponseDto;
import com.arman.fintech.user.entity.UserEntity;
import com.arman.fintech.user.service.UserService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping
public class UserController {
    UserService userService;
    UserController(UserService userService){
        this.userService=userService;
    }
    @PostMapping("/createusers")
    public ResponseEntity<UserResponseDto>createUsers(@RequestBody  UserRequestDto userrequestdto){
        UserResponseDto user=userService.createUsers(userrequestdto);
        return ResponseEntity.status(202).body(user);
    }
    @GetMapping("getusers/{id}")
    public ResponseEntity<UserRequestDto>getusers(@PathVariable int id){
        UserRequestDto userrequestDto=userService.getUsers(id);
        return ResponseEntity.status(200).body(userrequestDto);

    }


}
