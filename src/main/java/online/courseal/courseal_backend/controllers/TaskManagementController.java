package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.requests.CreateTaskRequest;
import online.courseal.courseal_backend.requests.UpdateCourseRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course-management")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TaskManagementController {
    @PostMapping("/{course_id}/task")
    public ResponseEntity<?> createTask(@RequestBody CreateTaskRequest createTaskRequest){
        return null;
    }

    @GetMapping("/{course_id}/task")
    public ResponseEntity<?> getTasksList(){
        return null;
    }

    @PutMapping("/{course_id}/task/{task_id}")
    public ResponseEntity<?> updateTask(@RequestBody UpdateCourseRequest updateCourseRequest){
        return null;
    }

    @DeleteMapping("/{course_id}/task/{task_id}")
    public ResponseEntity<?> deleteTask(){
        return null;
    }
}
