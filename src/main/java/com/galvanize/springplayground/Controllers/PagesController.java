package com.galvanize.springplayground.Controllers;

import com.galvanize.springplayground.services.WordCounter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PagesController {

    WordCounter counter;

    public PagesController(WordCounter counter){
        this.counter = counter;
    }

    @PostMapping("/words/count")
    public Map<String, Integer> countWords(@RequestBody String phrase){
        return counter.countWords(phrase.trim());
    }


    @GetMapping("/")
    public String getIndex(){
        return "GET to index route";
    }
}
