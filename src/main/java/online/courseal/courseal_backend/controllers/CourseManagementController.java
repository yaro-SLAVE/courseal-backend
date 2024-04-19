package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.models.Course;
import online.courseal.courseal_backend.models.User;
import online.courseal.courseal_backend.repositories.UserRepository;
import online.courseal.courseal_backend.requests.*;
import online.courseal.courseal_backend.responses.CreateCourseResponse;
import online.courseal.courseal_backend.services.CourseMaintainerService;
import online.courseal.courseal_backend.services.CourseService;
import online.courseal.courseal_backend.services.UserDetailsImpl;
import online.courseal.courseal_backend.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/course-management")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseManagementController {
    @Autowired
    CourseService courseService;

    @Autowired
    CourseMaintainerService courseMaintainerService;

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody CreateCourseRequest createCourseRequest) {
        Course course = courseService.createCourse(createCourseRequest.getCourseName(), createCourseRequest.getCourseDescription());

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findByUserTag(userDetails.getUserTag());

        courseMaintainerService.createCourseMaintainer(course, user.get());

        return ResponseEntity.ok(new CreateCourseResponse(course.getCourseId()));
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
