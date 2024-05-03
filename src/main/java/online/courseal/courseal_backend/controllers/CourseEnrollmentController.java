package online.courseal.courseal_backend.controllers;

import jakarta.websocket.server.PathParam;
import online.courseal.courseal_backend.errors.exceptions.BadRequestException;
import online.courseal.courseal_backend.models.Course;
import online.courseal.courseal_backend.models.CourseEnrollment;
import online.courseal.courseal_backend.models.User;
import online.courseal.courseal_backend.requests.EnrollingIntoCourseRequest;
import online.courseal.courseal_backend.responses.EnrolledCoursesListResponse;
import online.courseal.courseal_backend.services.CourseEnrollmentService;
import online.courseal.courseal_backend.services.CourseService;
import online.courseal.courseal_backend.services.UserDetailsImpl;
import online.courseal.courseal_backend.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/course-enrollment")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseEnrollmentController {
    @Autowired
    UserDetailsServiceImpl userService;

    @Autowired
    CourseService courseService;

    @Autowired
    CourseEnrollmentService courseEnrollmentService;

    @GetMapping
    public ResponseEntity<?> getEnrolledCoursesList() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> users = userService.findByUserTag(userDetails.getUserTag());

        ArrayList<EnrolledCoursesListResponse> response = new ArrayList<>();

        if (!users.get().getCourseEnrollments().isEmpty()) {
            for (CourseEnrollment courseEnrollment: users.get().getCourseEnrollments()) {
                response.add(new EnrolledCoursesListResponse(
                        courseEnrollment.getCourse().getCourseId(),
                        courseEnrollment.getCourse().getCourseName(),
                        courseEnrollment.getCourse().getCourseDescription(),
                        courseEnrollment.getXp()
                ));
            }
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> enrollIntoCourse(@RequestBody EnrollingIntoCourseRequest enrollingIntoCourseRequest) {
        return null;
    }

    @GetMapping("/{course_id}")
    public HttpStatus getEnrollmentCourseInfo(@PathVariable("course_id") Integer courseId) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> users = userService.findByUserTag(userDetails.getUserTag());

        Optional<Course> courses = courseService.findByCourseId(courseId);

        if (courses.isEmpty()){
            throw new BadRequestException();
        }

        CourseEnrollment courseEnrollment = courseEnrollmentService.createCourseEnrollment(courses.get(), users.get());

        return HttpStatus.CREATED;
    }

    @GetMapping("/{course_id}/rating")
    public ResponseEntity<?> getRating(@PathVariable("course_id") Integer courseId) {
        return null;
    }

    @PutMapping("/{course_id}/rating")
    public ResponseEntity<?> ratesCourse(@PathVariable("course_id") Integer courseId) {
        return null;
    }

    @GetMapping("/{course_id}/lesson/{lesson_id}")
    public ResponseEntity<?> getTasks(@PathVariable("course_id") Integer courseId) {
        return null;
    }

    @PutMapping("/{course_id}/lesson/{lesson_id}")
    public ResponseEntity<?> sendCompletingInfo(@PathVariable("course_id") Integer courseId) {
        return null;
    }

}
