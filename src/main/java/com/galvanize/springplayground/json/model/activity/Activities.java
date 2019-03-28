package com.galvanize.springplayground.json.model.activity;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

public class Activities {
    private List<Activity> activities;

    public Activities() {
    }
    public Activities(List<Activity> activities){
        this.activities = activities;
    }

    @JsonView({Views.DetailView.class, Views.CompactView.class})
    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
