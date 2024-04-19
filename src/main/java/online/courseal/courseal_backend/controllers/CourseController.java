package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.models.Course;
import online.courseal.courseal_backend.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping
    public ResponseEntity<?> getCoursesLists(){
        List<Course> courses = courseService.findAll();

        return null;
    }
}
