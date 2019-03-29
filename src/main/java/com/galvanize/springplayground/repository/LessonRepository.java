package com.galvanize.springplayground.repository;

import com.galvanize.springplayground.entity.Lesson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson, Long> {
    List<Lesson> findByTitleContaining(String title);

    List<Lesson> findByDeliveredOnBetween(Date date1, Date date2);
}
