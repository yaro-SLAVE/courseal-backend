package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.errors.exceptions.BadRequestException;
import online.courseal.courseal_backend.models.Course;
import online.courseal.courseal_backend.models.CourseEnrollment;
import online.courseal.courseal_backend.models.CourseMaintainer;
import online.courseal.courseal_backend.models.User;
import online.courseal.courseal_backend.repositories.UserRepository;
import online.courseal.courseal_backend.requests.*;
import online.courseal.courseal_backend.responses.CourseInfoResponse;
import online.courseal.courseal_backend.responses.MaintainerCoursesListResponse;
import online.courseal.courseal_backend.responses.CreateCourseResponse;
import online.courseal.courseal_backend.services.CourseMaintainerService;
import online.courseal.courseal_backend.services.CourseService;
import online.courseal.courseal_backend.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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

        List<CourseMaintainer> courseMaintainers = users.get().getCourseMaintainers();

        ArrayList<MaintainerCoursesListResponse> maintainerCoursesListRespons = new ArrayList<>();

        for (CourseMaintainer courseMaintainer: courseMaintainers.stream().toList()){
            maintainerCoursesListRespons.add(new MaintainerCoursesListResponse(
                    courseMaintainer.getCourse().getCourseId(),
                    courseMaintainer.getPermissions()));
        }

        return ResponseEntity.ok(maintainerCoursesListRespons);
    }

    @GetMapping("/{course_id}")
    public ResponseEntity<?> getCourseInfo(@PathVariable("course_id") Integer courseId){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> users = userRepository.findByUserTag(userDetails.getUserTag());

        Optional<Course> courses = courseService.findByCourseId(courseId);

        if (courses.isEmpty()){
            throw new BadRequestException();
        }

        boolean userIsMaintainer = courseService.verifyMaintainer(courses.get(), users.get());

        if (!userIsMaintainer) {
            throw new BadRequestException();
        }

        List<CourseEnrollment> courseEnrollments = courses.get().getCourseEnrollments();

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
    }

    @PutMapping("/{course_id}")
    public void updateCourseInfo(@RequestBody CourseUpdatingRequest courseUpdatingRequest, @PathVariable("course_id") Integer courseId){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> users = userRepository.findByUserTag(userDetails.getUserTag());

        Optional<Course> courses = courseService.findByCourseId(courseId);

        if (courses.isEmpty()){
            throw new BadRequestException();
        }

        boolean userIsMaintainer = courseService.verifyMaintainer(courses.get(), users.get());

        if (!userIsMaintainer) {
            throw new BadRequestException();
        }

        courses.get().setCourseName(courseUpdatingRequest.getCourseName());
        courses.get().setCourseDescription(courseUpdatingRequest.getCourseDescription());
        courseService.save(courses.get());
    }

    @DeleteMapping("/{course_id}")
    public void deleteCourse(@PathVariable("course_id") Integer courseId){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> users = userRepository.findByUserTag(userDetails.getUserTag());

        Optional<Course> courses = courseService.findByCourseId(courseId);

        if (courses.isEmpty()){
            throw new BadRequestException();
        }

        boolean userIsMaintainer = courseService.verifyMaintainer(courses.get(), users.get());

        if (!userIsMaintainer) {
            throw new BadRequestException();
        }

        courseService.delete(courses.get());
    }
}
