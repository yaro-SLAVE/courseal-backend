package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.models.Course;
import online.courseal.courseal_backend.models.CourseEnrollment;
import online.courseal.courseal_backend.responses.CoursesListsResponse;
import online.courseal.courseal_backend.services.CourseEnrollmentService;
import online.courseal.courseal_backend.services.CourseService;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/course")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController {
    @Autowired
    CourseService courseService;

    @Autowired
    CourseEnrollmentService courseEnrollmentService;

    @GetMapping
    public ResponseEntity<?> getCoursesLists(){
        List<Course> courses = courseService.findAll();

        ArrayList<CoursesListsResponse> response = new ArrayList<>();

        for (Course course: courses){
            int votes = 0;
            Optional<CourseEnrollment> courseEnrollments = courseEnrollmentService.findByCourse(course);

            if (!courseEnrollments.isEmpty()){
                for (CourseEnrollment courseEnrollment: courseEnrollments.stream().toList()){
                    votes += courseEnrollment.getRating();
                }
            }

            response.add(new CoursesListsResponse(
                    course.getCourseId(),
                    course.getCourseName(),
                    course.getCourseDescription(),
                    0
            ));
        }

        return ResponseEntity.ok(response);
    }
}
