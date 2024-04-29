package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.errors.exceptions.BadRequestException;
import online.courseal.courseal_backend.models.Course;
import online.courseal.courseal_backend.models.CourseLesson;
import online.courseal.courseal_backend.models.CourseLessonLecture;
import online.courseal.courseal_backend.models.User;
import online.courseal.courseal_backend.requests.LessonCreatingRequest;
import online.courseal.courseal_backend.requests.LessonUpdatingRequest;
import online.courseal.courseal_backend.responses.LessonCreatingResponse;
import online.courseal.courseal_backend.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/course-management")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonManagementController {
    @Autowired
    UserDetailsServiceImpl userService;

    @Autowired
    CourseService courseService;

    @Autowired
    CourseLessonService courseLessonService;

    @Autowired
    CourseLessonLectureService courseLessonLectureService;

    @PostMapping("/{course_id}/lesson")
    public ResponseEntity<?> createLesson(@RequestBody LessonCreatingRequest lessonCreatingRequest, @PathVariable("course_id") Integer courseId){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> users = userService.findByUserTag(userDetails.getUserTag());

        Optional<Course> courses = courseService.findByCourseId(courseId);

        if (courses.isEmpty()){
            throw new BadRequestException();
        }

        boolean userIsMaintainer = courseService.verifyMaintainer(courses.get(), users.get());

        if (!userIsMaintainer) {
            throw new BadRequestException();
        }

        CourseLesson courseLesson = courseLessonService.createCourseLesson(
                courses.get(),
                lessonCreatingRequest.getLessonName(),
                lessonCreatingRequest.getLessonProgressNeeded(),
                lessonCreatingRequest.getLesson().getType()
        );

        CourseLessonLecture courseLessonLecture = courseLessonLectureService.createCourseLessonLecture(
                courseLesson,
                lessonCreatingRequest.getLesson().getLessonContent()
        );

        return ResponseEntity.ok(new LessonCreatingResponse(courseLesson.getCourseLessonId()));
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
