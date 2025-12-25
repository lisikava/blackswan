package com.project.fin.controllers;

import com.project.fin.dto.LoginDto;
import com.project.fin.dto.RegisterDto;
import com.project.fin.exceptions.UserAlreadyExistException;
import com.project.fin.exceptions.UsernameNotFoundException;
import com.project.fin.models.User;
import com.project.fin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    UserService userService;
    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto dto) throws UserAlreadyExistException {
            User user = userService.register(dto);
            return ResponseEntity.ok("User registered");
    }
    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) throws UsernameNotFoundException {
        return ResponseEntity.ok(userService.login(dto));
    }
}
