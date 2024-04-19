package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.requests.CourseStructureUpdatingRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course-management")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseStructureManagementController {
    @GetMapping("/{course_id}/structure")
    public ResponseEntity<?> getCourseStructure(){
        return null;
    }

    @PutMapping("/{course_id}/structure")
    public ResponseEntity<?> updateCourseStructure(@RequestBody CourseStructureUpdatingRequest courseStructureUpdatingRequest){
        return null;
    }
}
