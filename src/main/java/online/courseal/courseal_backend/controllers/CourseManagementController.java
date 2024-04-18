package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.models.Course;
import online.courseal.courseal_backend.requests.*;
import online.courseal.courseal_backend.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course-management")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseManagementController {
    @Autowired
    CourseService courseService;

    @PostMapping
    public ResponseEntity<Integer> createCourse(@RequestBody CreateCourseRequest createCourseRequest){
        Course course = courseService.createCourse(createCourseRequest.getCourseName(), createCourseRequest.getCourseDescription());

        return new ResponseEntity<>(course.getCourseId(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getCoursesList(){
        return null;
    }

    @GetMapping("/{course_id}")
    public ResponseEntity<?> getCourseInfo(){
        return null;
    }

    @PutMapping("/{course_id}")
    public ResponseEntity<?> updateCourseInfo(@RequestBody UpdateCourseRequest updateCourseRequest){
        return null;
    }

    @DeleteMapping("/{course_id}")
    public ResponseEntity<?> deleteCourse(){
        return null;
    }
}
