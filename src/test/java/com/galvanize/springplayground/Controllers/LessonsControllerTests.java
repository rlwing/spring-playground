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
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

    @Test
    @Transactional
    @Rollback
    public void testUpdateLesson() throws Exception{
        Lesson lesson = new Lesson();
        lesson.setTitle("Original Lesson Title");
        lesson.setDeliveredOn(new Date());
        repository.save(lesson);

        String newLessonText = "Modified Lesson Title";

        MockHttpServletRequestBuilder request = patch(String.format("/lessons/%s", lesson.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("{ \"title\":\"%s\" }", newLessonText));

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("title", equalTo(newLessonText)));
    }

    @Test
    @Transactional
    @Rollback
    public void testFindByTitle() throws Exception{
        Lesson lesson = new Lesson("Lesson to look for", new Date());
        repository.save(lesson);

        MockHttpServletRequestBuilder request = get(String.format("/lessons/find/%s", lesson.getTitle()))
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", equalTo(lesson.getTitle())));

    }

    @Test
    @Transactional
    @Rollback
    public void testFindBetweenDates() throws Exception{
        Lesson lesson1 = new Lesson("Lesson date 1",
                new GregorianCalendar(2000, Calendar.FEBRUARY, 11).getTime());
        repository.save(lesson1);
        Lesson lesson2 = new Lesson("Lesson date 2",
                new GregorianCalendar(2000, Calendar.FEBRUARY, 12).getTime());
        repository.save(lesson2);

        this.mvc.perform(get(String.format("/lessons/between?date1=%s&date2=%s",
                                            "2000-02-01", "2000-02-20")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", equalTo("Lesson date 1")))
                .andExpect(jsonPath("$[1].title", equalTo("Lesson date 2")));
    }


}
