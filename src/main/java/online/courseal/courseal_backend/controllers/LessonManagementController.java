package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.coursedata.lessons.CoursealLessonExam;
import online.courseal.courseal_backend.coursedata.lessons.CoursealLessonLecture;
import online.courseal.courseal_backend.coursedata.lessons.CoursealLessonPractice;
import online.courseal.courseal_backend.coursedata.lessons.CoursealLessonTraining;
import online.courseal.courseal_backend.errors.exceptions.BadRequestException;
import online.courseal.courseal_backend.models.*;
import online.courseal.courseal_backend.models.enums.LessonType;
import online.courseal.courseal_backend.requests.LessonCreatingOrUpdatingRequest;
import online.courseal.courseal_backend.responses.LessonCreatingResponse;
import online.courseal.courseal_backend.responses.LessonsListResponse;
import online.courseal.courseal_backend.services.*;
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
    public ResponseEntity<?> createLesson(@RequestBody LessonCreatingOrUpdatingRequest lessonCreatingRequest, @PathVariable("course_id") Integer courseId){
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

        List<LessonsListResponse> lessonsList = new ArrayList<>();
        ArrayList<Integer> tasks = new ArrayList<>();

        for (CourseLesson courseLesson: courses.get().getCourseLessons()) {
            switch (courseLesson.getLessonType()) {
                case LECTURE:
                    lessonsList.add(new LessonsListResponse(
                            courseLesson.getCourseLessonId(),
                            courseLesson.getLessonName(),
                            courseLesson.getProgressNeeded(),
                            new CoursealLessonLecture(courseLesson.getCourseLessonLectures().getLecture())
                    ));
                    break;

                case EXAM:
                    for (CourseLessonTask courseLessonTask: courseLesson.getCourseLessonTasks()){
                        tasks.add(courseLessonTask.getCourseTask().getCourseTaskId());
                    }

                    lessonsList.add(new LessonsListResponse(
                            courseLesson.getCourseLessonId(),
                            courseLesson.getLessonName(),
                            courseLesson.getProgressNeeded(),
                            new CoursealLessonExam(tasks)
                    ));

                    tasks.clear();
                    break;

                case PRACTICE:
                    for (CourseLessonTask courseLessonTask: courseLesson.getCourseLessonTasks()){
                        tasks.add(courseLessonTask.getCourseTask().getCourseTaskId());
                    }

                    lessonsList.add(new LessonsListResponse(
                            courseLesson.getCourseLessonId(),
                            courseLesson.getLessonName(),
                            courseLesson.getProgressNeeded(),
                            new CoursealLessonPractice(tasks)
                    ));

                    tasks.clear();
                    break;

                case PRACTICE_TRAINING:
                    lessonsList.add(new LessonsListResponse(
                            courseLesson.getCourseLessonId(),
                            courseLesson.getLessonName(),
                            courseLesson.getProgressNeeded(),
                            new CoursealLessonTraining()
                    ));
                    break;
            }
        }

        return ResponseEntity.ok(lessonsList);
    }

    @PutMapping("/{course_id}/lesson/{lesson_id}")
    public void updateLesson(@RequestBody LessonCreatingOrUpdatingRequest lessonUpdatingRequest, @PathVariable("course_id") Integer courseId, @PathVariable("lesson_id") Integer lessonId){
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

        Optional<CourseLesson> courseLessons = courseLessonService.findByCourseLessonId(lessonId);

        if (courseLessons.isEmpty() || !courses.get().getCourseLessons().contains(courseLessons.get())) {
            throw new BadRequestException();
        }

        if (!courseLessons.get().getCourseLessonTasks().isEmpty()) {
            for (CourseLessonTask courseLessonTask: courseLessons.get().getCourseLessonTasks()) {
                courseLessonTaskService.delete(courseLessonTask);
            }
        }

        if (courseLessons.get().getCourseLessonLectures() != null) {
            courseLessonLectureService.delete(courseLessons.get().getCourseLessonLectures());
        }

        courseLessons.get().setLessonName(lessonUpdatingRequest.getLessonName());
        courseLessons.get().setProgressNeeded(lessonUpdatingRequest.getLessonProgressNeeded());

        switch (lessonUpdatingRequest.getLesson().getClass().getTypeName()) {
            case "lecture":
                courseLessons.get().setLessonType(LessonType.LECTURE);
                courseLessonLectureService.createCourseLessonLecture(
                        courseLessons.get(),
                        ((CoursealLessonLecture) lessonUpdatingRequest.getLesson()).getLectureContent()
                );
                break;

            case "exam":
                courseLessons.get().setLessonType(LessonType.EXAM);
                for (Integer courseTaskId: ((CoursealLessonExam) lessonUpdatingRequest.getLesson()).getTasks()) {
                    Optional<CourseTask> courseTasks = courseTaskService.findByCourseTaskId(courseTaskId);

                    if (courseTasks.isEmpty()) {
                        throw new BadRequestException();
                    }

                    if (!courses.get().getCourseTasks().contains(courseTasks.get())) {
                        throw new BadRequestException();
                    }

                    CourseLessonTask courseLessonTask = courseLessonTaskService.createCourseLessonTask(courseLessons.get(), courseTasks.get());
                }
                break;

            case "practice":
                courseLessons.get().setLessonType(LessonType.PRACTICE);
                for (Integer courseTaskId: ((CoursealLessonPractice) lessonUpdatingRequest.getLesson()).getTasks()) {
                    Optional<CourseTask> courseTasks = courseTaskService.findByCourseTaskId(courseTaskId);

                    if (courseTasks.isEmpty()) {
                        throw new BadRequestException();
                    }

                    if (!courses.get().getCourseTasks().contains(courseTasks.get())) {
                        throw new BadRequestException();
                    }

                    CourseLessonTask courseLessonTask = courseLessonTaskService.createCourseLessonTask(courseLessons.get(), courseTasks.get());
                }
                break;

            case "training":
                courseLessons.get().setLessonType(LessonType.PRACTICE_TRAINING);
                break;
        }
    }

    @DeleteMapping("/{course_id}/lesson/{lesson_id}")
    public void deleteLesson(@PathVariable("course_id") Integer courseId, @PathVariable("lesson_id") Integer lessonId){
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

        Optional<CourseLesson> courseLessons = courseLessonService.findByCourseLessonId(lessonId);

        if (courseLessons.isEmpty() || !courses.get().getCourseLessons().contains(courseLessons.get())) {
            throw new BadRequestException();
        }

        courseLessonService.delete(courseLessons.get());
    }
}