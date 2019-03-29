package com.galvanize.springplayground.repository;

import com.galvanize.springplayground.entity.Lesson;
import org.springframework.data.repository.CrudRepository;

public interface LessonRepository extends CrudRepository<Lesson, Long> {
}
