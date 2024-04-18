package online.courseal.courseal_backend.services;

import online.courseal.courseal_backend.models.Course;
import online.courseal.courseal_backend.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public Optional<Course> findByCourseId(Integer courseId){
        return courseRepository.findByCourseId(courseId);
    }

    public Course createCourse(String courseName, String courseDescription){
        Course course = new Course(courseName, courseDescription);
        course.setLastUpdated(LocalDateTime.now());

        course = courseRepository.save(course);
        return course;
    }
}
