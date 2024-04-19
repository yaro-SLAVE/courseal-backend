package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.errors.exceptions.BadRequestException;
import online.courseal.courseal_backend.errors.exceptions.InvalidJwtException;
import online.courseal.courseal_backend.models.Course;
import online.courseal.courseal_backend.models.CourseEnrollment;
import online.courseal.courseal_backend.models.CourseMaintainer;
import online.courseal.courseal_backend.models.User;
import online.courseal.courseal_backend.repositories.CourseRepository;
import online.courseal.courseal_backend.repositories.UserRepository;
import online.courseal.courseal_backend.requests.*;
import online.courseal.courseal_backend.responses.CourseInfoResponse;
import online.courseal.courseal_backend.responses.CoursesListResponse;
import online.courseal.courseal_backend.responses.CreateCourseResponse;
import online.courseal.courseal_backend.services.CourseEnrollmentService;
import online.courseal.courseal_backend.services.CourseMaintainerService;
import online.courseal.courseal_backend.services.CourseService;
import online.courseal.courseal_backend.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    CourseEnrollmentService courseEnrollmentService;

    @Autowired
    CourseRepository courseRepository;

    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody CourseCreatingRequest courseCreatingRequest) {
        Course course = courseService.createCourse(courseCreatingRequest.getCourseName(), courseCreatingRequest.getCourseDescription());

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> users = userRepository.findByUserTag(userDetails.getUserTag());

        courseMaintainerService.createCourseMaintainer(course, users.get());

        return ResponseEntity.ok(new CreateCourseResponse(course.getCourseId()));
    }

    @GetMapping
    public ResponseEntity<?> getCoursesList(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> users = userRepository.findByUserTag(userDetails.getUserTag());

        Optional<CourseMaintainer> courseMaintainers = courseMaintainerService.findByUser(users.get());

        ArrayList<CoursesListResponse> coursesListResponses = new ArrayList<>();

        for (CourseMaintainer courseMaintainer: courseMaintainers.stream().toList()){
            coursesListResponses.add(new CoursesListResponse(
                    courseMaintainer.getCourse().getCourseId(),
                    courseMaintainer.getPermissions()));
        }

        return ResponseEntity.ok(coursesListResponses);
    }

    @GetMapping("/{course_id}")
    public ResponseEntity<?> getCourseInfo(@PathVariable("course_id") Integer courseId){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> users = userRepository.findByUserTag(userDetails.getUserTag());

        Optional<Course> courses = courseService.findByCourseId(courseId);

        if (courses.isEmpty()){
            throw new BadRequestException();
        }

        Optional<CourseMaintainer> courseMaintainers = courseMaintainerService.findByCourse(courses.get());

        boolean userIsMaintainer = courseMaintainerService.verifyMaintainer(courseMaintainers, users.get(), courses.get());

        if (userIsMaintainer) {
            Optional<CourseEnrollment> courseEnrollments = courseEnrollmentService.findByCourse(courses.get());

            Integer votes = 0;
            if (!courseEnrollments.isEmpty()) {
                for (CourseEnrollment courseEnrollment : courseEnrollments.stream().toList()) {
                    votes += courseEnrollment.getRating();
                }
            }

            return ResponseEntity.ok(new CourseInfoResponse(
                    courses.get().getCourseName(),
                    courses.get().getCourseDescription(),
                    votes,
                    courses.get().getLastUpdatedStructure(),
                    courses.get().getLastUpdatedLessons(),
                    courses.get().getLastUpdatedTasks()));
        } else {
            throw new InvalidJwtException();
        }
    }

    @PutMapping("/{course_id}")
    public HttpStatus updateCourseInfo(@RequestBody CourseUpdatingRequest courseUpdatingRequest, @PathVariable("course_id") Integer courseId){
        courseService.findByCourseId(courseId)
                .map(course -> {
                    UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    Optional<User> users = userRepository.findByUserTag(userDetails.getUserTag());

                    Optional<CourseMaintainer> courseMaintainers = courseMaintainerService.findByCourse(course);

                    boolean userIsMaintainer = courseMaintainerService.verifyMaintainer(courseMaintainers, users.get(), course);

                    if (userIsMaintainer) {
                        course.setCourseName(courseUpdatingRequest.getCourseName());
                        course.setCourseDescription(courseUpdatingRequest.getCourseDescription());
                        courseRepository.save(course);
                        return null;
                    } else {
                        throw new InvalidJwtException();
                    }
                });
        return HttpStatus.OK;
    }

    @DeleteMapping("/{course_id}")
    public ResponseEntity<?> deleteCourse(@PathVariable("course_id") Integer courseId){
        return null;
    }
}
