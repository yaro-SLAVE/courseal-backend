package online.courseal.courseal_backend.repositories;

import online.courseal.courseal_backend.models.Course;
import online.courseal.courseal_backend.models.CourseEnrollment;
import online.courseal.courseal_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, Integer> {
    Optional<CourseEnrollment> findByCourseEnrollmentId(Integer courseEnrollmentId);

    Optional<CourseEnrollment> findByCourse(Course course);

    List<CourseEnrollment> findByCourseAndUser(Course course, User user);
}
