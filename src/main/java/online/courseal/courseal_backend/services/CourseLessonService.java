package online.courseal.courseal_backend.services;

import online.courseal.courseal_backend.models.Course;
import online.courseal.courseal_backend.models.CourseLesson;
import online.courseal.courseal_backend.models.enums.LessonType;
import online.courseal.courseal_backend.repositories.CourseLessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CourseLessonService {
    @Autowired
    CourseLessonRepository courseLessonRepository;

    public Optional<CourseLesson> findByCourseLessonId(Integer courseLessonId) {
        return courseLessonRepository.findByCourseLessonId(courseLessonId);
    }

    public CourseLesson createCourseLesson(Course course, String lessonName, Integer progressNeeded, LessonType lessonType) {
        CourseLesson courseLesson = new CourseLesson(
                course,
                lessonName,
                null,
                lessonType,
                progressNeeded,
                LocalDateTime.now()
        );

        courseLesson = courseLessonRepository.save(courseLesson);

        return courseLesson;
    }

    public void save(CourseLesson courseLesson) {
        courseLessonRepository.save(courseLesson);
    }

    public void delete(CourseLesson courseLesson) {
        courseLessonRepository.delete(courseLesson);
    }
}
