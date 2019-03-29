package com.galvanize.springplayground.Controllers;

import com.galvanize.springplayground.entity.Lesson;
import com.galvanize.springplayground.json.model.Flight;
import com.galvanize.springplayground.repository.LessonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;

import java.text.DateFormat;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LessonsControllerTests {
    @Autowired
    MockMvc mvc;

    @Autowired
    LessonRepository repository;

    /*
    @Test
    @Transactional
    @Rollback
    public void testCreate() throws Exception {
        MockHttpServletRequestBuilder request = post("/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\": \"Chloeaa\"}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", instanceOf(Number.class) ));
    }

    @Test
    @Transactional
    @Rollback
    public void testList() throws Exception {
        Person person = new Person();
        person.setFirstName("Lou");
        repository.save(person);

        MockHttpServletRequestBuilder request = get("/people")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(person.getId().intValue()) ));
    }
     */
    @Test
    @Transactional
    @Rollback
    public void testGetLessons() throws Exception{
        Lesson lesson = new Lesson();
        lesson.setTitle("This is my test title");
        lesson.setDeliveredOn(new Date());
        repository.save(lesson);

        MockHttpServletRequestBuilder request = get(String.format("/lessons/%s", lesson.getId()))
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("title", equalTo("This is my test title")));
    }
}
