package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.requests.LessonCreatingRequest;
import online.courseal.courseal_backend.requests.LessonUpdatingRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course-management")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonManagementController {
    @PostMapping("/{course_id}/lesson")
    public ResponseEntity<?> createLesson(@RequestBody LessonCreatingRequest lessonCreatingRequest){
        return null;
    }

    @GetMapping("/{course_id}/lesson")
    public ResponseEntity<?> getLessonsList(){
        return null;
    }

    @PutMapping("/{course_id}/lesson/{lesson_id}")
    public ResponseEntity<?> updateLesson(@RequestBody LessonUpdatingRequest lessonUpdatingRequest){
        return null;
    }

    @DeleteMapping("/{course_id}/lesson/{lesson_id}")
    public ResponseEntity<?> deleteLesson(){
        return null;
    }
}
