package online.courseal.courseal_backend.services;

import online.courseal.courseal_backend.models.Course;
import online.courseal.courseal_backend.models.CourseEnrollment;
import online.courseal.courseal_backend.repositories.CourseEnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
