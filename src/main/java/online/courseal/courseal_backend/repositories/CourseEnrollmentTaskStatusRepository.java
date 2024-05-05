package online.courseal.courseal_backend.repositories;

import online.courseal.courseal_backend.models.CourseEnrollment;
import online.courseal.courseal_backend.models.CourseEnrollmentTaskStatus;
import online.courseal.courseal_backend.models.CourseTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseEnrollmentTaskStatusRepository extends JpaRepository<CourseEnrollmentTaskStatus, Integer> {
    Optional<CourseEnrollmentTaskStatus> findByCourseEnrollmentTaskStatusId(Integer courseEnrollmentTaskStatusId);

    List<CourseEnrollmentTaskStatus> findByCourseEnrollmentAndCourseTask(CourseEnrollment courseEnrollment, CourseTask courseTask);
}
