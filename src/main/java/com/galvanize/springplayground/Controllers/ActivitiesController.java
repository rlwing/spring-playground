package com.galvanize.springplayground.Controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.galvanize.springplayground.json.model.activity.Activities;
import com.galvanize.springplayground.json.model.activity.Views;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/activities")
public class ActivitiesController {

    @PostMapping(value = "/simplify", produces = "application/vnd.galvanize.compact+json")
    @JsonView(Views.CompactView.class)
    public Activities simplifyCompact(@RequestBody Activities activities){
        return activities;
    }

    @PostMapping(value = "/simplify", produces = "application/vnd.galvanize.detailed+json")
    @JsonView(Views.DetailView.class)
    public Activities simplifyDetail(@RequestBody Activities activities){
        return activities;
    }

    @PostMapping("/simplify")
    public Activities simplifyFull(@RequestBody Activities activities){
        return activities;
    }
}
