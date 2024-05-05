package online.courseal.courseal_backend.services;

import online.courseal.courseal_backend.models.CourseEnrollment;
import online.courseal.courseal_backend.models.CourseEnrollmentTaskStatus;
import online.courseal.courseal_backend.models.CourseTask;
import online.courseal.courseal_backend.repositories.CourseEnrollmentTaskStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseEnrollmentTaskStatusService {
    @Autowired
    CourseEnrollmentTaskStatusRepository courseEnrollmentTaskStatusRepository;

    public Optional<CourseEnrollmentTaskStatus> findByCourseEnrollmentTaskStatusId(Integer courseEnrollmentTaskStatusId) {
        return courseEnrollmentTaskStatusRepository.findByCourseEnrollmentTaskStatusId(courseEnrollmentTaskStatusId);
    }

    public List<CourseEnrollmentTaskStatus> findByCourseEnrollmentAndCourseTask(CourseEnrollment courseEnrollment, CourseTask courseTask) {
        return courseEnrollmentTaskStatusRepository.findByCourseEnrollmentAndCourseTask(courseEnrollment, courseTask);
    }

    public void createCourseEnrollmentTaskStatus(CourseEnrollment courseEnrollment, CourseTask courseTask, Integer timesDone, Integer timesFailed) {
        CourseEnrollmentTaskStatus courseEnrollmentTaskStatus = new CourseEnrollmentTaskStatus(
                courseEnrollment,
                courseTask,
                timesDone,
                timesFailed
        );

        courseEnrollmentTaskStatusRepository.save(courseEnrollmentTaskStatus);
    }

    public void save(CourseEnrollmentTaskStatus courseEnrollmentTaskStatus) {
        courseEnrollmentTaskStatusRepository.save(courseEnrollmentTaskStatus);
    }

    public void delete(CourseEnrollmentTaskStatus courseEnrollmentTaskStatus) {
        courseEnrollmentTaskStatusRepository.delete(courseEnrollmentTaskStatus);
    }
}
