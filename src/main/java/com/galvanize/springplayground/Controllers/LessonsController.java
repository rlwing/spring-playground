package com.galvanize.springplayground.Controllers;

import com.galvanize.springplayground.entity.Lesson;
import com.galvanize.springplayground.repository.LessonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Optional;

@RestController
@RequestMapping("/lessons")
public class LessonsController {

    private final LessonRepository repository;

    public LessonsController(LessonRepository repository){
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Lesson> all(){
        return this.repository.findAll();
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson){
        return this.repository.save(lesson);
    }

    @GetMapping("/{id}")
    public Optional<Lesson> getByKey(@PathVariable Long id){
        return this.repository.findById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id){
        this.repository.deleteById(id);
        return String.format("Lesson %s was deleted", id);
    }
}
