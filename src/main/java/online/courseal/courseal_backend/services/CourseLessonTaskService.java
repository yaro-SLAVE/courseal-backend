package online.courseal.courseal_backend.services;

import online.courseal.courseal_backend.models.CourseLesson;
import online.courseal.courseal_backend.models.CourseLessonTask;
import online.courseal.courseal_backend.models.CourseTask;
import online.courseal.courseal_backend.repositories.CourseLessonTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseLessonTaskService {
    @Autowired
    CourseLessonTaskRepository courseLessonTaskRepository;

    public Optional<CourseLessonTask> findByCourseLessonTaskId(Integer courseLessonTaskId) {
        return courseLessonTaskRepository.findByCourseLessonTaskId(courseLessonTaskId);
    }

    public CourseLessonTask createCourseLessonTask(CourseLesson courseLesson, CourseTask courseTask) {
        CourseLessonTask courseLessonTask = new CourseLessonTask(
                courseLesson,
                courseTask
        );

        courseLessonTask = courseLessonTaskRepository.save(courseLessonTask);

        return courseLessonTask;
    }

    public void save(CourseLessonTask courseLessonTask) {
        courseLessonTaskRepository.save(courseLessonTask);
    }

    public void delete(CourseLessonTask courseLessonTask) {
        courseLessonTaskRepository.delete(courseLessonTask);
    }
}
