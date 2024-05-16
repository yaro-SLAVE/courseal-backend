package online.courseal.courseal_backend.controllers;

import jakarta.validation.Valid;
import online.courseal.courseal_backend.errors.exceptions.BadRequestException;
import online.courseal.courseal_backend.errors.exceptions.CourseNotFoundException;
import online.courseal.courseal_backend.models.Course;
import online.courseal.courseal_backend.models.CourseTask;
import online.courseal.courseal_backend.models.User;
import online.courseal.courseal_backend.requests.TaskCreatingRequest;
import online.courseal.courseal_backend.requests.TaskUpdatingRequest;
import online.courseal.courseal_backend.responses.TaskCreatingResponse;
import online.courseal.courseal_backend.responses.TasksListResponse;
import online.courseal.courseal_backend.services.CourseService;
import online.courseal.courseal_backend.services.CourseTaskService;
import online.courseal.courseal_backend.services.UserDetailsImpl;
import online.courseal.courseal_backend.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/course-management")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TaskManagementController {
    @Autowired
    UserDetailsServiceImpl userService;

    @Autowired
    CourseService courseService;

    @Autowired
    CourseTaskService courseTaskService;


    @PostMapping("/{course_id}/task")
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskCreatingRequest taskCreatingRequest, @PathVariable("course_id") Integer courseId){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> users = this.userService.findByUserTag(userDetails.getUserTag());

        Optional<Course> courses = courseService.findByCourseId(courseId);

        if (courses.isEmpty()){
            throw new BadRequestException();
        }

        boolean userIsMaintainer = courseService.verifyMaintainer(courses.get(), users.get());

        if (!userIsMaintainer) {
            throw new BadRequestException();
        }

        CourseTask courseTask = courseTaskService.createCourseTask(courses.get(), taskCreatingRequest.getTaskName(), taskCreatingRequest.getTask());

        courses.get().setLastUpdatedTasks(LocalDateTime.now());
        courseService.save(courses.get());

        return ResponseEntity.ok(new TaskCreatingResponse(courseTask.getCourseTaskId()));
    }

    @GetMapping("/{course_id}/task")
    public ResponseEntity<?> getTasksList(@PathVariable("course_id") Integer courseId){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> users = this.userService.findByUserTag(userDetails.getUserTag());

        Optional<Course> courses = courseService.findByCourseId(courseId);

        if (courses.isEmpty()){
            throw new BadRequestException();
        }

        boolean userIsMaintainer = courseService.verifyMaintainer(courses.get(), users.get());

        if (!userIsMaintainer) {
            throw new BadRequestException();
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
    public HttpStatus updateTask(@RequestBody TaskUpdatingRequest taskUpdatingRequest, @PathVariable("course_id") Integer courseId, @PathVariable("task_id") Integer taskId){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> users = this.userService.findByUserTag(userDetails.getUserTag());

        Optional<Course> courses = courseService.findByCourseId(courseId);

        if (courses.isEmpty()){
            throw new CourseNotFoundException();
        }

        boolean userIsMaintainer = courseService.verifyMaintainer(courses.get(), users.get());

        if (!userIsMaintainer) {
            throw new BadRequestException();
        }

        Optional<CourseTask> courseTasks = courseTaskService.findByCourseTaskId(taskId);

        if (courseTasks.isEmpty()) {
            throw new BadRequestException();
        }

        courses.get().setLastUpdatedTasks(LocalDateTime.now());
        courseService.save(courses.get());

        courseTasks.get().setTaskName(taskUpdatingRequest.getTaskName());
        courseTasks.get().setTask(taskUpdatingRequest.getTask());
        courseTaskService.save(courseTasks.get());

        return HttpStatus.NO_CONTENT;
    }

    @DeleteMapping("/{course_id}/task/{task_id}")
    public HttpStatus deleteTask(@PathVariable("course_id") Integer courseId, @PathVariable("task_id") Integer taskId){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> users = this.userService.findByUserTag(userDetails.getUserTag());

        Optional<Course> courses = courseService.findByCourseId(courseId);

        if (courses.isEmpty()){
            throw new CourseNotFoundException();
        }

        boolean userIsMaintainer = courseService.verifyMaintainer(courses.get(), users.get());

        if (!userIsMaintainer) {
            throw new BadRequestException();
        }

        Optional<CourseTask> courseTasks = courseTaskService.findByCourseTaskId(taskId);

        if (courseTasks.isEmpty()) {
            throw new BadRequestException();
        }

        courses.get().setLastUpdatedTasks(LocalDateTime.now());
        courseService.save(courses.get());

        courseTaskService.delete(courseTasks.get());

        return HttpStatus.NO_CONTENT;
    }
}
