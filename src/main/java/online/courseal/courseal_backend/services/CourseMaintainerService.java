package online.courseal.courseal_backend.services;

import online.courseal.courseal_backend.models.Course;
import online.courseal.courseal_backend.models.CourseMaintainer;
import online.courseal.courseal_backend.models.User;
import online.courseal.courseal_backend.models.enums.CoursePermissions;
import online.courseal.courseal_backend.repositories.CourseMaintainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseMaintainerService {
    @Autowired
    CourseMaintainerRepository courseMaintainerRepository;

    public void createCourseMaintainer(Course course, User user){
        CourseMaintainer courseMaintainer = new CourseMaintainer(course, user);
        courseMaintainer.setPermissions(CoursePermissions.FULL);
        courseMaintainer = courseMaintainerRepository.save(courseMaintainer);
    }
}