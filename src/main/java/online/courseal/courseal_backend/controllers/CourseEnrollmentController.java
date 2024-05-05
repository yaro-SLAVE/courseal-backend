package online.courseal.courseal_backend.controllers;

import jakarta.websocket.server.PathParam;
import online.courseal.courseal_backend.errors.exceptions.BadRequestException;
import online.courseal.courseal_backend.errors.exceptions.CourseNotFoundException;
import online.courseal.courseal_backend.models.*;
import online.courseal.courseal_backend.requests.EnrollingIntoCourseRequest;
import online.courseal.courseal_backend.responses.EnrolledCoursesListResponse;
import online.courseal.courseal_backend.responses.data.EnrollmentCourseLessonData;
import online.courseal.courseal_backend.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    CourseEnrollmentLessonStatusService courseEnrollmentLessonStatusService;

    @Autowired
    CourseEnrollmentTaskStatusService courseEnrollmentTaskStatusService;

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
    public HttpStatus enrollIntoCourse(@RequestBody EnrollingIntoCourseRequest enrollingIntoCourseRequest) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> users = userService.findByUserTag(userDetails.getUserTag());

        Optional<Course> courses = courseService.findByCourseId(enrollingIntoCourseRequest.getCourseId());

        if (courses.isEmpty()){
            throw new CourseNotFoundException();
        }

        CourseEnrollment courseEnrollment = courseEnrollmentService.createCourseEnrollment(courses.get(), users.get());

        return HttpStatus.CREATED;
    }

    @GetMapping("/{course_id}")
    public ResponseEntity<?> getEnrollmentCourseInfo(@PathVariable("course_id") Integer courseId) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> users = userService.findByUserTag(userDetails.getUserTag());

        Optional<Course> courses = courseService.findByCourseId(courseId);

        if (courses.isEmpty()) {
            throw new CourseNotFoundException();
        }

        List<CourseEnrollment> courseEnrollments = courseEnrollmentService.findByCourseAndUser(courses.get(), users.get());

        if (courseEnrollments.isEmpty()) {
            throw new CourseNotFoundException();
        }

        ArrayList<EnrollmentCourseLessonData> data = new ArrayList<>();


        return null;
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
