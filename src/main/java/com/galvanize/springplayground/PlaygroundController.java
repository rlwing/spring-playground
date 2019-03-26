package com.galvanize.springplayground;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlaygroundController {

    @GetMapping("/")
    public String hello(){
        return "Hello from Spring!";
    }
}
