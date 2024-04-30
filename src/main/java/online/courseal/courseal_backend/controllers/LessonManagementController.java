package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.coursedata.lessons.CoursealLessonExam;
import online.courseal.courseal_backend.coursedata.lessons.CoursealLessonLecture;
import online.courseal.courseal_backend.coursedata.lessons.CoursealLessonPractice;
import online.courseal.courseal_backend.coursedata.lessons.CoursealLessonTraining;
import online.courseal.courseal_backend.errors.exceptions.BadRequestException;
import online.courseal.courseal_backend.models.*;
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

    @Autowired
    CourseLessonTaskService courseLessonTaskService;

    @Autowired
    CourseTaskService courseTaskService;

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
                lessonCreatingRequest.getLesson().getClass().getTypeName()
        );

        switch (lessonCreatingRequest.getLesson().getClass().getTypeName()) {
            case "lecture":
                courseLessonLectureService.createCourseLessonLecture(
                        courseLesson,
                        ((CoursealLessonLecture) lessonCreatingRequest.getLesson()).getLectureContent()
                );
                break;

            case "exam":
                for (Integer courseTaskId: ((CoursealLessonExam) lessonCreatingRequest.getLesson()).getTasks()) {
                    Optional<CourseTask> courseTasks = courseTaskService.findByCourseTaskId(courseTaskId);

                    if (courseTasks.isEmpty()) {
                        throw new BadRequestException();
                    }

                    if (!courses.get().getCourseTasks().contains(courseTasks.get())) {
                        throw new BadRequestException();
                    }

                    CourseLessonTask courseLessonTask = courseLessonTaskService.createCourseLessonTask(courseLesson, courseTasks.get());
                }
                break;

            case "practice":
                for (Integer courseTaskId: ((CoursealLessonPractice) lessonCreatingRequest.getLesson()).getTasks()) {
                    Optional<CourseTask> courseTasks = courseTaskService.findByCourseTaskId(courseTaskId);

                    if (courseTasks.isEmpty()) {
                        throw new BadRequestException();
                    }

                    if (!courses.get().getCourseTasks().contains(courseTasks.get())) {
                        throw new BadRequestException();
                    }

                    CourseLessonTask courseLessonTask = courseLessonTaskService.createCourseLessonTask(courseLesson, courseTasks.get());
                }
                break;
        }

        return ResponseEntity.ok(new LessonCreatingResponse(courseLesson.getCourseLessonId()));
    }

    @GetMapping("/{course_id}/lesson")
    public ResponseEntity<?> getLessonsList(@PathVariable("course_id") Integer courseId){
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
