package com.NewsAggregator.news_Aggregator.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class hello{

    @GetMapping("/Hello")
    public String hello(){
        return "Hello World!";
    }

    @GetMapping("/helloworld")
    public String helloFuc(){
        return "this is learner mangement system ";
    }
}