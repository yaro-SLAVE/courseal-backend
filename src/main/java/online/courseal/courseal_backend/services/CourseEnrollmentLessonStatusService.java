package online.courseal.courseal_backend.services;

import online.courseal.courseal_backend.models.CourseEnrollment;
import online.courseal.courseal_backend.models.CourseEnrollmentLessonStatus;
import online.courseal.courseal_backend.models.CourseLesson;
import online.courseal.courseal_backend.repositories.CourseEnrollmentLessonStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@Service
public class CourseEnrollmentLessonStatusService {
    @Autowired
    CourseEnrollmentLessonStatusRepository courseEnrollmentLessonStatusRepository;

    public Optional<CourseEnrollmentLessonStatus> findByCourseEnrollmentLessonStatusId(Integer courseEnrollmentLessonStatusId) {
        return courseEnrollmentLessonStatusRepository.findByCourseEnrollmentLessonStatusId(courseEnrollmentLessonStatusId);
    }

    public List<CourseEnrollmentLessonStatus> findByCourseEnrollmentAndCourseLesson(CourseEnrollment courseEnrollment, CourseLesson courseLesson) {
        return courseEnrollmentLessonStatusRepository.findByCourseEnrollmentAndCourseLesson(courseEnrollment, courseLesson);
    }

    public Boolean existsByCourseEnrollmentAndCourseLesson(CourseEnrollment courseEnrollment, CourseLesson courseLesson) {
        return courseEnrollmentLessonStatusRepository.existsByCourseEnrollmentAndCourseLesson(courseEnrollment, courseLesson);
    }

    public void createCourseEnrollmentLessonStatus(CourseEnrollment courseEnrollment, CourseLesson courseLesson, Integer lessonProgress, TimeZone timezone) {
        CourseEnrollmentLessonStatus courseEnrollmentLessonStatus = new CourseEnrollmentLessonStatus(
                courseEnrollment,
                courseLesson,
                lessonProgress,
                LocalDateTime.now().atZone(timezone.toZoneId()).toLocalDateTime()
        );

        courseEnrollmentLessonStatusRepository.save(courseEnrollmentLessonStatus);
    }

    public void save(CourseEnrollmentLessonStatus courseEnrollmentLessonStatus) {
        courseEnrollmentLessonStatusRepository.save(courseEnrollmentLessonStatus);
    }

    public void delete(CourseEnrollmentLessonStatus courseEnrollmentLessonStatus) {
        courseEnrollmentLessonStatusRepository.delete(courseEnrollmentLessonStatus);
    }
}
