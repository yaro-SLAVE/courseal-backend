package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.errors.exceptions.CourseNotFoundException;
import online.courseal.courseal_backend.errors.exceptions.LessonNotFoundException;
import online.courseal.courseal_backend.models.*;
import online.courseal.courseal_backend.requests.EnrollingIntoCourseRequest;
import online.courseal.courseal_backend.requests.EnrollmentCourseUserRatingRequest;
import online.courseal.courseal_backend.requests.LessonCompletingRequest;
import online.courseal.courseal_backend.responses.EnrolledCoursesListResponse;
import online.courseal.courseal_backend.responses.EnrollmentCourseInfoResponse;
import online.courseal.courseal_backend.responses.EnrollmentCourseUserRatingResponse;
import online.courseal.courseal_backend.responses.data.EnrollmentCourseLessonData;
import online.courseal.courseal_backend.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/course-enrollment")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseEnrollmentController {
    @Autowired
    UserDetailsServiceImpl userService;

    @Autowired
    CourseService courseService;

    @Autowired
    CourseLessonService courseLessonService;

    @Autowired
    CourseEnrollmentService courseEnrollmentService;

    @Autowired
    CourseEnrollmentLessonStatusService courseEnrollmentLessonStatusService;

    @Autowired
    CourseEnrollmentTaskStatusService courseEnrollmentTaskStatusService;

    @Autowired
    LessonTokenService lessonTokenService;

    @GetMapping
    public ResponseEntity<?> getEnrolledCoursesList() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> users = userService.findByUserTag(userDetails.getUserTag());

        ArrayList<EnrolledCoursesListResponse> response = new ArrayList<>();

        if (!users.get().getCourseEnrollments().isEmpty()) {
            for (CourseEnrollment courseEnrollment: users.get().getCourseEnrollments()) {
                response.add(new EnrolledCoursesListResponse(
                        courseEnrollment.getCourse().getCourseId(),
                        courseEnrollment.getCourse().getCourseName(),
                        courseEnrollment.getCourse().getCourseDescription(),
                        courseEnrollment.getXp()
                ));
            }
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public HttpStatus enrollIntoCourse(@RequestBody EnrollingIntoCourseRequest enrollingIntoCourseRequest) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> users = userService.findByUserTag(userDetails.getUserTag());

        Optional<Course> courses = courseService.findByCourseId(enrollingIntoCourseRequest.getCourseId());

        if (courses.isEmpty()){
            throw new CourseNotFoundException();
        }

        CourseEnrollment courseEnrollment = courseEnrollmentService.createCourseEnrollment(courses.get(), users.get());

        return HttpStatus.CREATED;
    }

    @GetMapping("/{course_id}")
    public ResponseEntity<?> getEnrollmentCourseInfo(@PathVariable("course_id") Integer courseId) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> users = userService.findByUserTag(userDetails.getUserTag());

        Optional<Course> courses = courseService.findByCourseId(courseId);

        if (courses.isEmpty()) {
            throw new CourseNotFoundException();
        }

        List<CourseEnrollment> courseEnrollments = courseEnrollmentService.findByCourseAndUser(courses.get(), users.get());

        if (courseEnrollments.isEmpty()) {
            throw new CourseNotFoundException();
        }

        List<List<EnrollmentCourseLessonData>> dataList = new ArrayList<>();

        if (!courses.get().getCourseLessons().isEmpty()) {
            Optional<CourseLesson> courseLessons = courses.get()
                    .getCourseLessons().stream()
                    .filter(c -> c.getLessonLevel() != null)
                    .max(Comparator.comparing(CourseLesson::getLessonLevel));

            if (!courseLessons.isEmpty()) {
                Integer maxLevel = courseLessons.get().getLessonLevel();

                boolean previousLevelIsCompleted = true;

                for (int i = 0; i <= maxLevel; ++i) {
                    List<CourseLesson> courseLessons1 = courseLessonService.findByLessonLevelAndCourse(i, courses.get());
                    ArrayList<EnrollmentCourseLessonData> data = new ArrayList<>();
                    boolean currentLevelCanBeDone = previousLevelIsCompleted;

                    for (CourseLesson courseLesson : courseLessons1.stream().toList()) {
                        data.add(new EnrollmentCourseLessonData(
                                courseLesson.getCourseLessonId(),
                                courseLesson.getLessonName(),
                                !courseLesson.getCourseEnrollmentLessonStatuses().isEmpty()
                                        ? courseLesson.getCourseEnrollmentLessonStatuses().getFirst().getProgress()
                                        : 0,
                                courseLesson.getProgressNeeded(),
                                currentLevelCanBeDone
                        ));

                        if (courseLesson.getCourseEnrollmentLessonStatuses().isEmpty() ||
                                courseLesson.getCourseEnrollmentLessonStatuses().getFirst().getProgress() <
                                        courseLesson.getProgressNeeded()) {
                            previousLevelIsCompleted = false;
                        } else if (courseLesson.getCourseEnrollmentLessonStatuses().getFirst().getProgress() >=
                                courseLesson.getProgressNeeded()) {
                            previousLevelIsCompleted = true;
                        }
                    }
                    dataList.add(data);
                }
            }
        }

        int votes = 0;

        for (CourseEnrollment courseEnrollment: courses.get().getCourseEnrollments()){
            votes += courseEnrollment.getRating();
        }

        return ResponseEntity.ok(new EnrollmentCourseInfoResponse(
                courses.get().getCourseId(),
                courses.get().getCourseName(),
                courses.get().getCourseDescription(),
                votes,
                0,
                dataList
        ));
    }

    @GetMapping("/{course_id}/rating")
    public ResponseEntity<?> getRating(@PathVariable("course_id") Integer courseId) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> users = userService.findByUserTag(userDetails.getUserTag());

        Optional<Course> courses = courseService.findByCourseId(courseId);

        if (courses.isEmpty()) {
            throw new CourseNotFoundException();
        }

        List<CourseEnrollment> courseEnrollments = courseEnrollmentService.findByCourseAndUser(courses.get(), users.get());

        if (courseEnrollments.isEmpty()) {
            throw new CourseNotFoundException();
        }

        return ResponseEntity.ok(new EnrollmentCourseUserRatingResponse(courseEnrollments.get(0).getRating()));
    }

    @PutMapping("/{course_id}/rating")
    public HttpStatus ratesCourse(@PathVariable("course_id") Integer courseId, @RequestBody EnrollmentCourseUserRatingRequest enrollmentCourseUserRatingRequest) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> users = userService.findByUserTag(userDetails.getUserTag());

        Optional<Course> courses = courseService.findByCourseId(courseId);

        if (courses.isEmpty()) {
            throw new CourseNotFoundException();
        }

        List<CourseEnrollment> courseEnrollments = courseEnrollmentService.findByCourseAndUser(courses.get(), users.get());

        if (courseEnrollments.isEmpty()) {
            throw new CourseNotFoundException();
        }

        courseEnrollments.getFirst().setRating(enrollmentCourseUserRatingRequest.getRating());
        courseEnrollmentService.save(courseEnrollments.getFirst());

        return HttpStatus.NO_CONTENT;
    }

    @GetMapping("/{course_id}/lesson/{lesson_id}")
    public ResponseEntity<?> getTasks(@PathVariable("course_id") Integer courseId, @PathVariable("lesson_id") Integer lessonId) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> users = userService.findByUserTag(userDetails.getUserTag());

        Optional<Course> courses = courseService.findByCourseId(courseId);

        if (courses.isEmpty()) {
            throw new CourseNotFoundException();
        }

        List<CourseEnrollment> courseEnrollments = courseEnrollmentService.findByCourseAndUser(courses.get(), users.get());

        if (courseEnrollments.isEmpty()) {
            throw new CourseNotFoundException();
        }

        Optional<CourseLesson> courseLessons = courseLessonService.findByCourseLessonId(lessonId);

        if (courseLessons.isEmpty()) {
            throw new LessonNotFoundException();
        }

        //LessonToken lessonToken = lessonTokenService.createLessonToken(courseLessons.get());

        return null;
    }

    @PutMapping("/{course_id}/lesson/{lesson_id}")
    public ResponseEntity<?> sendCompletingInfo(@PathVariable("course_id") Integer courseId, @PathVariable("lesson_id") Integer lessonId, @RequestBody LessonCompletingRequest lessonCompletingRequest) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> users = userService.findByUserTag(userDetails.getUserTag());

        Optional<Course> courses = courseService.findByCourseId(courseId);

        if (courses.isEmpty()) {
            throw new CourseNotFoundException();
        }

        List<CourseEnrollment> courseEnrollments = courseEnrollmentService.findByCourseAndUser(courses.get(), users.get());

        if (courseEnrollments.isEmpty()) {
            throw new CourseNotFoundException();
        }

        return null;
    }

}
