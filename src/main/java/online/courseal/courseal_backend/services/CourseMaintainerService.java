package online.courseal.courseal_backend.services;

import online.courseal.courseal_backend.errors.exceptions.InvalidJwtException;
import online.courseal.courseal_backend.models.Course;
import online.courseal.courseal_backend.models.CourseMaintainer;
import online.courseal.courseal_backend.models.User;
import online.courseal.courseal_backend.models.enums.CoursePermissions;
import online.courseal.courseal_backend.repositories.CourseMaintainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class CourseMaintainerService {
    @Autowired
    CourseMaintainerRepository courseMaintainerRepository;

    public Optional<CourseMaintainer> findByCourseMaintainerId(Integer courseMaintainerId) {
        return courseMaintainerRepository.findByCourseMaintainerId(courseMaintainerId);
    }

    public Optional<CourseMaintainer> findByUser(User user) {
        return courseMaintainerRepository.findByUser(user);
    }

    public Optional<CourseMaintainer> findByCourse(Course course) {
        return courseMaintainerRepository.findByCourse(course);
    }

    public void createCourseMaintainer(Course course, User user){
        CourseMaintainer courseMaintainer = new CourseMaintainer(course, user);
        courseMaintainer.setPermissions(CoursePermissions.FULL);
        courseMaintainerRepository.save(courseMaintainer);
    }
}
