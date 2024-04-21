package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.errors.exceptions.BadRequestException;
import online.courseal.courseal_backend.errors.exceptions.InvalidJwtException;
import online.courseal.courseal_backend.models.Course;
import online.courseal.courseal_backend.models.User;
import online.courseal.courseal_backend.repositories.UserRepository;
import online.courseal.courseal_backend.requests.TaskCreatingRequest;
import online.courseal.courseal_backend.requests.CourseUpdatingRequest;
import online.courseal.courseal_backend.services.CourseService;
import online.courseal.courseal_backend.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/course-management")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TaskManagementController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseService courseService;


    @PostMapping("/{course_id}/task")
    public ResponseEntity<?> createTask(@RequestBody TaskCreatingRequest taskCreatingRequest, @PathVariable("course_id") Integer courseId){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> users = userRepository.findByUserTag(userDetails.getUserTag());

        Optional<Course> courses = courseService.findByCourseId(courseId);

        if (courses.isEmpty()){
            throw new BadRequestException();
        }

        boolean userIsMaintainer = courseService.verifyMaintainer(courses.get(), users.get());

        if (userIsMaintainer) {
            return null;
        } else {
            throw new InvalidJwtException();
        }
    }

    @GetMapping("/{course_id}/task")
    public ResponseEntity<?> getTasksList(@PathVariable("course_id") Integer courseId){
        return null;
    }

    @PutMapping("/{course_id}/task/{task_id}")
    public ResponseEntity<?> updateTask(@RequestBody CourseUpdatingRequest courseUpdatingRequest, @PathVariable("course_id") Integer courseId, @PathVariable("task_id") Integer taskId){
        return null;
    }

    @DeleteMapping("/{course_id}/task/{task_id}")
    public ResponseEntity<?> deleteTask(@PathVariable("course_id") Integer courseId, @PathVariable("task_id") Integer taskId){
        return null;
    }
}
