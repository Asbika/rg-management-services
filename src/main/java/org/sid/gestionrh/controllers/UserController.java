package org.sid.gestionrh.controllers;

import org.sid.gestionrh.models.requests.LoginRequest.LoginRequest;
import org.sid.gestionrh.models.response.LoginResponse.LoginResponse;
import org.sid.gestionrh.services.RegesterRequest.RegesterRequest;
import org.sid.gestionrh.services.UserResponse.UserResponse;
import org.sid.gestionrh.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return new ResponseEntity<>(userService.login(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegesterRequest registerRequest){
        return new ResponseEntity<>(userService.register(registerRequest),HttpStatus.CREATED);
    }
}
