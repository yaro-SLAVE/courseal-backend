package online.courseal.courseal_backend.repositories;

import online.courseal.courseal_backend.models.Course;
import online.courseal.courseal_backend.models.CourseEnrollment;
import online.courseal.courseal_backend.models.CourseEnrollmentLessonStatus;
import online.courseal.courseal_backend.models.CourseLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseEnrollmentLessonStatusRepository extends JpaRepository<CourseEnrollmentLessonStatus, Integer> {
    Optional<CourseEnrollmentLessonStatus> findByCourseEnrollmentLessonStatusId(Integer courseEnrollmentLessonStatusId);

    List<CourseEnrollmentLessonStatus> findByCourseEnrollmentAndCourseLesson(CourseEnrollment courseEnrollment, CourseLesson courseLesson);
}
