package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.errors.exceptions.BadRequestException;
import online.courseal.courseal_backend.errors.exceptions.InvalidJwtException;
import online.courseal.courseal_backend.models.Course;
import online.courseal.courseal_backend.models.CourseTask;
import online.courseal.courseal_backend.models.User;
import online.courseal.courseal_backend.repositories.UserRepository;
import online.courseal.courseal_backend.requests.TaskCreatingRequest;
import online.courseal.courseal_backend.requests.TaskUpdatingRequest;
import online.courseal.courseal_backend.responses.TaskCreatingResponse;
import online.courseal.courseal_backend.responses.TasksListResponse;
import online.courseal.courseal_backend.services.CourseService;
import online.courseal.courseal_backend.services.CourseTaskService;
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
public class TaskManagementController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseService courseService;

    @Autowired
    CourseTaskService courseTaskService;


    @PostMapping("/{course_id}/task")
    public ResponseEntity<?> createTask(@RequestBody TaskCreatingRequest taskCreatingRequest, @PathVariable("course_id") Integer courseId){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> users = userRepository.findByUserTag(userDetails.getUserTag());

        Optional<Course> courses = courseService.findByCourseId(courseId);

        if (courses.isEmpty()){
            throw new BadRequestException();
        }

        boolean userIsMaintainer = courseService.verifyMaintainer(courses.get(), users.get());

        if (!userIsMaintainer) {
            throw new InvalidJwtException();
        }

        CourseTask courseTask = courseTaskService.createCourseTask(courses.get(), taskCreatingRequest.getTaskName(), taskCreatingRequest.getTask());

        return ResponseEntity.ok(new TaskCreatingResponse(courseTask.getCourseTaskId()));
    }

    @GetMapping("/{course_id}/task")
    public ResponseEntity<?> getTasksList(@PathVariable("course_id") Integer courseId){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> users = userRepository.findByUserTag(userDetails.getUserTag());

        Optional<Course> courses = courseService.findByCourseId(courseId);

        if (courses.isEmpty()){
            throw new BadRequestException();
        }

        boolean userIsMaintainer = courseService.verifyMaintainer(courses.get(), users.get());

        if (!userIsMaintainer) {
            throw new InvalidJwtException();
        }

        ArrayList<TasksListResponse> tasksListResponse = new ArrayList<>();

        for (CourseTask courseTask: courses.get().getCourseTasks()) {
            tasksListResponse.add(new TasksListResponse(
                    courseTask.getCourseTaskId(),
                    courseTask.getTaskName(),
                    courseTask.getTask()
            ));
        }

        return ResponseEntity.ok(tasksListResponse);
    }

    @PutMapping("/{course_id}/task/{task_id}")
    public void updateTask(@RequestBody TaskUpdatingRequest taskUpdatingRequest, @PathVariable("course_id") Integer courseId, @PathVariable("task_id") Integer taskId){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> users = userRepository.findByUserTag(userDetails.getUserTag());

        Optional<Course> courses = courseService.findByCourseId(courseId);

        if (courses.isEmpty()){
            throw new BadRequestException();
        }

        boolean userIsMaintainer = courseService.verifyMaintainer(courses.get(), users.get());

        if (!userIsMaintainer) {
            throw new InvalidJwtException();
        }

        Optional<CourseTask> courseTasks = courseTaskService.findByCourseTaskId(taskId);

        if (courseTasks.isEmpty()) {
            throw new BadRequestException();
        }

        courseTasks.get().setTaskName(taskUpdatingRequest.getTaskName());
        courseTasks.get().setTask(taskUpdatingRequest.getTask());
        courseTaskService.save(courseTasks.get());
    }

    @DeleteMapping("/{course_id}/task/{task_id}")
    public void deleteTask(@PathVariable("course_id") Integer courseId, @PathVariable("task_id") Integer taskId){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> users = userRepository.findByUserTag(userDetails.getUserTag());

        Optional<Course> courses = courseService.findByCourseId(courseId);

        if (courses.isEmpty()){
            throw new BadRequestException();
        }

        boolean userIsMaintainer = courseService.verifyMaintainer(courses.get(), users.get());

        if (!userIsMaintainer) {
            throw new InvalidJwtException();
        }

        Optional<CourseTask> courseTasks = courseTaskService.findByCourseTaskId(taskId);

        if (courseTasks.isEmpty()) {
            throw new BadRequestException();
        }

        courseTaskService.delete(courseTasks.get());
    }
}
