package com.jewel.onlineelectoralsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("api/home")
    public ResponseEntity<Object> getHomePage(){
        return new ResponseEntity<>("Home page", HttpStatus.OK);
    }
}
