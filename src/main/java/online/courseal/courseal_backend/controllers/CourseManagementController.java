package online.courseal.courseal_backend.controllers;

import jakarta.persistence.Id;
import online.courseal.courseal_backend.requests.CreateCourseRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course-management")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseManagementController {
    @PostMapping("/create-course")
    public ResponseEntity<?> createCourse(@RequestBody CreateCourseRequest createCourseRequest, @CookieValue(value = "courseal_jwt", required = false) String jwtCookie){
        return null;
    }

    @PostMapping("/create-task")
    public ResponseEntity<?> createTask(@RequestBody CreateCourseRequest createTaskRequest, @CookieValue(value = "courseal_jwt", required = false) String jwtCookie){
        return null;
    }

    @PostMapping("/create-lesson")
    public ResponseEntity<?> createLesson(@RequestBody CreateCourseRequest createLessonRequest, @CookieValue(value = "courseal_jwt", required = false) String jwtCookie){
        return null;
    }
}
