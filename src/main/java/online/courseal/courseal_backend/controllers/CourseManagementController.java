package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.models.Course;
import online.courseal.courseal_backend.models.CourseMaintainer;
import online.courseal.courseal_backend.models.User;
import online.courseal.courseal_backend.repositories.UserRepository;
import online.courseal.courseal_backend.requests.*;
import online.courseal.courseal_backend.responses.CoursesListResponse;
import online.courseal.courseal_backend.responses.CreateCourseResponse;
import online.courseal.courseal_backend.services.CourseMaintainerService;
import online.courseal.courseal_backend.services.CourseService;
import online.courseal.courseal_backend.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findByUserTag(userDetails.getUserTag());

        Optional<CourseMaintainer> courseMaintainer = courseMaintainerService.findByUser(user.get());

        ArrayList<CoursesListResponse> coursesListResponse = new ArrayList<>();

        for (CourseMaintainer courseMaintainer1: courseMaintainer.stream().toList()){
            coursesListResponse.add(new CoursesListResponse(courseMaintainer1.getCourse().getCourseId(), courseMaintainer1.getPermissions()));
        }

        return ResponseEntity.ok(coursesListResponse);
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
