package online.courseal.courseal_backend.services;

import online.courseal.courseal_backend.models.Course;
import online.courseal.courseal_backend.models.CourseEnrollment;
import online.courseal.courseal_backend.models.User;
import online.courseal.courseal_backend.repositories.CourseEnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseEnrollmentService {
    @Autowired
    CourseEnrollmentRepository courseEnrollmentRepository;

    public Optional<CourseEnrollment> findByCourseEnrollmentId(Integer courseEnrollmentId) {
        return courseEnrollmentRepository.findByCourseEnrollmentId(courseEnrollmentId);
    }

    public Optional<CourseEnrollment> findByCourse(Course course) {
        return courseEnrollmentRepository.findByCourse(course);
    }

    public List<CourseEnrollment> findByCourseAndUser(Course course, User user) {
        return courseEnrollmentRepository.findByCourseAndUser(course, user);
    }

    public void createCourseEnrollment(Course course, User user) {
        CourseEnrollment courseEnrollment = new CourseEnrollment(
                course,
                user
        );

        courseEnrollmentRepository.save(courseEnrollment);
    }

    public void save(CourseEnrollment courseEnrollment) {
        courseEnrollmentRepository.save(courseEnrollment);
    }

    public void delete(CourseEnrollment courseEnrollment) {
        courseEnrollmentRepository.delete(courseEnrollment);
    }
}
