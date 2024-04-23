package online.courseal.courseal_backend.services;

import online.courseal.courseal_backend.models.Course;
import online.courseal.courseal_backend.models.CourseMaintainer;
import online.courseal.courseal_backend.models.User;
import online.courseal.courseal_backend.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public Optional<Course> findByCourseId(Integer courseId){
        return courseRepository.findByCourseId(courseId);
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course createCourse(String courseName, String courseDescription){
        Course course = new Course(courseName, courseDescription);
        course.setLastUpdatedStructure(LocalDateTime.now());
        course.setLastUpdatedLessons(LocalDateTime.now());
        course.setLastUpdatedTasks(LocalDateTime.now());

        course = courseRepository.save(course);
        return course;
    }

    public boolean verifyMaintainer(Course course, User user) {
        boolean flag = false;

        for (CourseMaintainer courseMaintainer: course.getCourseMaintainers()) {
            if (courseMaintainer.getUser().equals(user)){
                flag = true;
                break;
            }
        }

        return flag;
    }

    public void save(Course course) {
        courseRepository.save(course);
    }

    public void delete(Course course) {
        courseRepository.delete(course);
    }
}
