package com.info.groove.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Home.BASIS)
public class Home {
    
    public static final String BASIS = "/";

    @GetMapping
    public String home() {
        return "Hello world";
    }
}
