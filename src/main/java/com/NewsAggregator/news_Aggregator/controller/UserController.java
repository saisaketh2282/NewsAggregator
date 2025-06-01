package com.NewsAggregator.news_Aggregator.controller;

import com.NewsAggregator.news_Aggregator.Entity.Users;
import com.NewsAggregator.news_Aggregator.dto.userdto;
import com.NewsAggregator.news_Aggregator.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/hello")
    public String Hello(){
        return "hello welcome to my page";
    }

    @PostMapping("/register")
    public Users register(@RequestBody userdto user){
    return authenticationService.registerUser(user);
            }
}
