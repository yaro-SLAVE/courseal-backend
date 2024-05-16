package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.coursedata.enrolltaskcomplete.EnrollTasksCompleteExam;
import online.courseal.courseal_backend.coursedata.enrolltaskcomplete.EnrollTasksCompleteLecture;
import online.courseal.courseal_backend.coursedata.enrolltaskcomplete.EnrollTasksCompletePracticeTraining;
import online.courseal.courseal_backend.coursedata.enrolltaskcomplete.data.TaskExamAnswer;
import online.courseal.courseal_backend.coursedata.enrolltaskcomplete.data.TaskMultipleExamAnswer;
import online.courseal.courseal_backend.coursedata.enrolltaskcomplete.data.TaskPracticeTrainingAnswer;
import online.courseal.courseal_backend.coursedata.enrolltaskcomplete.data.TaskSingleExamAnswer;
import online.courseal.courseal_backend.coursedata.enrolltasks.EnrollTask;
import online.courseal.courseal_backend.coursedata.enrolltasks.EnrollTaskExam;
import online.courseal.courseal_backend.coursedata.enrolltasks.EnrollTaskLecture;
import online.courseal.courseal_backend.coursedata.enrolltasks.EnrollTaskPracticeTraining;
import online.courseal.courseal_backend.coursedata.enrolltasks.data.CoursealLessonEnrollExamTask;
import online.courseal.courseal_backend.coursedata.enrolltasks.data.CoursealLessonEnrollPracticeTask;
import online.courseal.courseal_backend.coursedata.examtasks.CoursealExamTask;
import online.courseal.courseal_backend.coursedata.examtasks.CoursealExamTaskMultiple;
import online.courseal.courseal_backend.coursedata.examtasks.CoursealExamTaskSingle;
import online.courseal.courseal_backend.coursedata.examtasks.data.ExamTaskMultipleOption;
import online.courseal.courseal_backend.coursedata.tasks.CoursealTask;
import online.courseal.courseal_backend.coursedata.tasks.CoursealTaskMultiple;
import online.courseal.courseal_backend.coursedata.tasks.CoursealTaskSingle;
import online.courseal.courseal_backend.coursedata.tasks.TaskMultipleOption;
import online.courseal.courseal_backend.errors.exceptions.CourseNotFoundException;
import online.courseal.courseal_backend.errors.exceptions.LessonNotFoundException;
import online.courseal.courseal_backend.errors.exceptions.LessonTokenNotFoundException;
import online.courseal.courseal_backend.models.*;
import online.courseal.courseal_backend.requests.EnrollingIntoCourseRequest;
import online.courseal.courseal_backend.requests.EnrollmentCourseUserRatingRequest;
import online.courseal.courseal_backend.requests.LessonCompletingRequest;
import online.courseal.courseal_backend.responses.*;
import online.courseal.courseal_backend.responses.data.EnrollmentCourseLessonData;
import online.courseal.courseal_backend.responses.data.LessonMistakesData;
import online.courseal.courseal_backend.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Autowired
    CourseTaskService courseTaskService;

    @Autowired
    UserActivityService userActivityService;

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

        courseEnrollmentService.createCourseEnrollment(courses.get(), users.get());

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
            Optional<CourseLesson> courseLessons = courseEnrollments.getFirst().getCourse()
                    .getCourseLessons().stream()
                    .filter(c -> c.getLessonLevel() != null)
                    .max(Comparator.comparing(CourseLesson::getLessonLevel));

            if (courseLessons.isPresent()) {
                Integer maxLevel = courseLessons.get().getLessonLevel();

                boolean previousLevelIsCompleted = true;

                for (int i = 0; i <= maxLevel; ++i) {
                    List<CourseLesson> courseLessons1 = courseLessonService.findByLessonLevelAndCourse(i, courseEnrollments.getFirst().getCourse());
                    ArrayList<EnrollmentCourseLessonData> data = new ArrayList<>();
                    boolean currentLevelCanBeDone = previousLevelIsCompleted;

                    for (CourseLesson courseLesson : courseLessons1.stream().toList()) {
                        String type = "";
                        switch(courseLesson.getLessonType()) {
                            case EXAM -> type = "exam";
                            case PRACTICE -> type = "practice";
                            case PRACTICE_TRAINING -> type = "training";
                            case LECTURE -> type = "lecture";
                        }

                        List<CourseEnrollmentLessonStatus> courseEnrollmentLessonStatuses = courseEnrollmentLessonStatusService.findByCourseEnrollmentAndCourseLesson(courseEnrollments.getFirst(), courseLesson);

                        data.add(new EnrollmentCourseLessonData(
                                courseLesson.getCourseLessonId(),
                                courseLesson.getLessonName(),
                                type,
                                !courseEnrollments.getFirst().getCourseEnrollmentLessonStatuses().isEmpty()
                                        ? courseEnrollmentLessonStatuses.getFirst().getProgress()
                                        : 0,
                                courseLesson.getProgressNeeded(),
                                currentLevelCanBeDone
                        ));

                        if (courseEnrollments.getFirst().getCourseEnrollmentLessonStatuses().isEmpty() ||
                                courseEnrollmentLessonStatuses.getFirst().getProgress() <
                                        courseLesson.getProgressNeeded()) {
                            previousLevelIsCompleted = false;
                        } else if (courseEnrollmentLessonStatuses.getFirst().getProgress() >=
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

        ArrayList<Integer> tasksId = new ArrayList<>();
        EnrollTask tasks = null;

        switch (courseLessons.get().getLessonType()) {
            case LECTURE -> tasks = new EnrollTaskLecture(courseLessons.get().getCourseLessonLecture().getLecture());

            case EXAM -> {
                List<CoursealLessonEnrollExamTask> examList = new ArrayList<>();
                for (CourseLessonTask courseLessonTask: courseLessons.get().getCourseLessonTasks()) {
                    tasksId.add(courseLessonTask.getCourseTask().getCourseTaskId());

                    CoursealExamTask examTask;
                    switch (courseLessonTask.getCourseTask().getTask()) {
                        case CoursealTaskSingle single -> examTask = new CoursealExamTaskSingle(
                                single.getBody(),
                                single.getOptions()
                        );

                        case CoursealTaskMultiple multiple -> {
                            List<ExamTaskMultipleOption> options = new ArrayList<>();

                            for (TaskMultipleOption option: multiple.getOptions()) {
                                options.add(new ExamTaskMultipleOption(option.getText()));
                            }

                            examTask = new CoursealExamTaskMultiple(
                                    multiple.getBody(),
                                    options
                            );
                        }
                    }

                    examList.add(new CoursealLessonEnrollExamTask(
                            courseLessonTask.getCourseTask().getCourseTaskId(),
                            examTask
                    ));
                }

                tasks = new EnrollTaskExam(examList);
            }

            case PRACTICE, PRACTICE_TRAINING -> {
                List<CoursealLessonEnrollPracticeTask> taskList = new ArrayList<>();
                for (CourseLessonTask courseLessonTask: courseLessons.get().getCourseLessonTasks()) {
                    tasksId.add(courseLessonTask.getCourseTask().getCourseTaskId());

                    CoursealTask task = courseLessonTask.getCourseTask().getTask();
                    taskList.add(new CoursealLessonEnrollPracticeTask(
                            courseLessonTask.getCourseTask().getCourseTaskId(),
                            task
                    ));
                }
                
                tasks = new EnrollTaskPracticeTraining(taskList);
            }

        }

        LessonToken lessonToken = lessonTokenService.createLessonToken(courseLessons.get(), tasksId);

        return ResponseEntity.ok(new EnrollmentCourseGettingTasksResponse(lessonToken.getLessonToken(), tasks));
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

        Optional<CourseLesson> courseLessons = courseLessonService.findByCourseLessonId(lessonId);

        if (courseLessons.isEmpty()) {
            throw new LessonNotFoundException();
        }

        Optional<LessonToken> lessonTokens = lessonTokenService.findByLessonToken(lessonCompletingRequest.getLessonToken());

        if (lessonTokens.isEmpty()) {
            throw new LessonTokenNotFoundException();
        }

        int xp = 0;
        List<LessonMistakesData> mistakes = new ArrayList<>();
        boolean completed = false;

        switch (lessonCompletingRequest.getTask()) {
            case EnrollTasksCompleteLecture ignored -> {
                if (!courseEnrollmentLessonStatusService.existsByCourseEnrollmentAndCourseLesson(courseEnrollments.getFirst(), courseLessons.get())) {
                    courseEnrollmentLessonStatusService.createCourseEnrollmentLessonStatus(
                            courseEnrollments.getFirst(),
                            courseLessons.get(),
                            1
                    );

                    xp += 15;
                    completed = true;
                } else {
                    completed = true;
                }
            }

            case EnrollTasksCompleteExam exam -> {
                for (TaskExamAnswer taskExamAnswer: exam.getTasks()) {
                    Optional<CourseTask> courseTasks = courseTaskService.findByCourseTaskId(taskExamAnswer.getTaskId());

                    int failed = 0;

                    switch (taskExamAnswer.getAnswer()) {
                        case TaskSingleExamAnswer singleAnswer -> {
                            switch(courseTasks.get().getTask()) {
                                case CoursealTaskSingle singleTask -> {
                                    if (singleTask.getCorrectOption().equals(singleAnswer.getOption())) {
                                        mistakes.add(new LessonMistakesData(
                                                courseTasks.get().getCourseTaskId(),
                                                true
                                        ));
                                    } else {
                                        mistakes.add(new LessonMistakesData(
                                                courseTasks.get().getCourseTaskId(),
                                                false
                                        ));

                                        failed = 1;
                                    }
                                }

                                case CoursealTaskMultiple ignored -> {}
                            }
                        }

                        case TaskMultipleExamAnswer multipleAnswer -> {
                            switch (courseTasks.get().getTask()) {
                                case CoursealTaskSingle ignored -> {}

                                case CoursealTaskMultiple multipleTask -> {
                                    boolean isCorrect = true;

                                    for (Integer option: multipleAnswer.getOptions()) {
                                        if (!multipleTask.getOptions().get(option).getIsCorrect()) {
                                            isCorrect = false;
                                            break;
                                        }
                                    }

                                    if (isCorrect) {
                                        for (int i = 0; i < multipleTask.getOptions().size(); ++i) {
                                            if (multipleTask.getOptions().get(i).getIsCorrect()) {
                                                if (!multipleAnswer.getOptions().contains(i)) {
                                                    isCorrect = false;
                                                    break;
                                                }
                                            }
                                        }
                                    }

                                    if (isCorrect) {
                                        mistakes.add(new LessonMistakesData(
                                                courseTasks.get().getCourseTaskId(),
                                                true
                                        ));
                                    } else {
                                        mistakes.add(new LessonMistakesData(
                                                courseTasks.get().getCourseTaskId(),
                                                false
                                        ));

                                        failed = 1;
                                    }
                                }
                            }
                        }
                    }

                    if (courseEnrollmentTaskStatusService.existsByCourseEnrollmentAndCourseTask
                            (courseEnrollments.getFirst(), courseTasks.get())) {
                        courseEnrollmentTaskStatusService.createCourseEnrollmentTaskStatus(
                                courseEnrollments.getFirst(),
                                courseTasks.get(),
                                1,
                                failed
                        );
                    } else {
                        List<CourseEnrollmentTaskStatus> courseEnrollmentTaskStatuses = courseEnrollmentTaskStatusService
                                .findByCourseEnrollmentAndCourseTask(
                                        courseEnrollments.getFirst(),
                                        courseTasks.get()
                                );

                        courseEnrollmentTaskStatuses.getFirst().setTimesDone(courseEnrollmentTaskStatuses.getFirst().getTimesDone() + 1);
                        courseEnrollmentTaskStatuses.getFirst().setTimesFailed(courseEnrollmentTaskStatuses.getFirst().getTimesFailed() + failed);

                        courseEnrollmentTaskStatusService.save(courseEnrollmentTaskStatuses.getFirst());
                    }
                }

                if (!courseEnrollmentLessonStatusService.existsByCourseEnrollmentAndCourseLesson(courseEnrollments.getFirst(), courseLessons.get())) {
                    courseEnrollmentLessonStatusService.createCourseEnrollmentLessonStatus(
                            courseEnrollments.getFirst(),
                            courseLessons.get(),
                            1
                    );
                } else {
                    List<CourseEnrollmentLessonStatus> courseEnrollmentLessonStatuses= courseEnrollmentLessonStatusService
                            .findByCourseEnrollmentAndCourseLesson(
                                    courseEnrollments.getFirst(),
                                    courseLessons.get()
                            );

                    courseEnrollmentLessonStatuses.getFirst().setProgress(courseEnrollmentLessonStatuses.getFirst().getProgress() + 1);

                    LocalDateTime timeFinished = LocalDateTime.now();

                    if (courseEnrollmentLessonStatuses.getFirst().getProgress() == courseLessons.get().getProgressNeeded()) {
                        xp += 15;
                        completed = true;
                    } else if (courseEnrollmentLessonStatuses.getFirst().getProgress() > courseLessons.get().getProgressNeeded()) {
                        xp += 10;

                        if (courseEnrollmentLessonStatuses.getFirst().getLastDone().plusMinutes(10).isAfter(timeFinished)) {
                            xp += 5;
                        }

                        completed = true;
                    }

                    courseEnrollmentLessonStatuses.getFirst().setLastDone(timeFinished);

                    courseEnrollmentLessonStatusService.save(courseEnrollmentLessonStatuses.getFirst());
                }
            }

            case EnrollTasksCompletePracticeTraining practice -> {
                for (TaskPracticeTrainingAnswer practiceAnswer: practice.getTasks()) {
                    int failed = 0;

                    if (practiceAnswer.getCorrect()) {
                        mistakes.add(new LessonMistakesData(
                                practiceAnswer.getTaskId(),
                                true
                        ));
                    } else {
                        mistakes.add(new LessonMistakesData(
                                practiceAnswer.getTaskId(),
                                false
                        ));

                        failed = 1;
                    }

                    if (!courseEnrollmentTaskStatusService.existsByCourseEnrollmentAndCourseTask(
                            courseEnrollments.getFirst(),
                            courseTaskService.findByCourseTaskId(practiceAnswer.getTaskId()).get())) {
                        courseEnrollmentTaskStatusService.createCourseEnrollmentTaskStatus(
                                courseEnrollments.getFirst(),
                                courseTaskService.findByCourseTaskId(practiceAnswer.getTaskId()).get(),
                                1,
                                failed
                        );
                    } else {
                        List<CourseEnrollmentTaskStatus> courseEnrollmentTaskStatus = courseEnrollmentTaskStatusService
                                .findByCourseEnrollmentAndCourseTask(
                                        courseEnrollments.getFirst(),
                                        courseTaskService.findByCourseTaskId(practiceAnswer.getTaskId()).get()
                                );

                        courseEnrollmentTaskStatus.getFirst().setTimesDone(courseEnrollmentTaskStatus.getFirst().getTimesDone() + 1);
                        courseEnrollmentTaskStatus.getFirst().setTimesFailed(courseEnrollmentTaskStatus.getFirst().getTimesFailed() + failed);

                        courseEnrollmentTaskStatusService.save(courseEnrollmentTaskStatus.getFirst());
                    }
                }

                if (!courseEnrollmentLessonStatusService.existsByCourseEnrollmentAndCourseLesson(courseEnrollments.getFirst(), courseLessons.get())) {
                    courseEnrollmentLessonStatusService.createCourseEnrollmentLessonStatus(
                            courseEnrollments.getFirst(),
                            courseLessons.get(),
                            1
                    );
                } else {
                    List<CourseEnrollmentLessonStatus> courseEnrollmentLessonStatuses= courseEnrollmentLessonStatusService
                            .findByCourseEnrollmentAndCourseLesson(
                                    courseEnrollments.getFirst(),
                                    courseLessons.get()
                            );

                    courseEnrollmentLessonStatuses.getFirst().setProgress(courseEnrollmentLessonStatuses.getFirst().getProgress() + 1);

                    LocalDateTime timeFinished = LocalDateTime.now();

                    if (courseEnrollmentLessonStatuses.getFirst().getProgress() == courseLessons.get().getProgressNeeded()) {
                        xp += 15;
                        completed = true;
                    } else if (courseEnrollmentLessonStatuses.getFirst().getProgress() > courseLessons.get().getProgressNeeded()) {
                        xp += 10;

                        if (courseEnrollmentLessonStatuses.getFirst().getLastDone().plusMinutes(10).isAfter(timeFinished)) {
                            xp += 5;
                        }

                        completed = true;
                    }

                    courseEnrollmentLessonStatuses.getFirst().setLastDone(timeFinished);

                    courseEnrollmentLessonStatusService.save(courseEnrollmentLessonStatuses.getFirst());


                }
            }
        }

        courseEnrollments.getFirst().setXp(courseEnrollments.getFirst().getXp() + xp);
        courseEnrollmentService.save(courseEnrollments.getFirst());

        LocalDate day = LocalDateTime.now().atZone(lessonCompletingRequest.getTimeZone().toZoneId()).toLocalDate();

        List<UserActivity> userActivities = userActivityService.findByUserAndDay(users.get(), day);

        if (!userActivities.isEmpty()) {
            userActivities.getFirst().setXp(userActivities.getFirst().getXp() + xp);
            userActivityService.save(userActivities.getFirst());
        } else {
            userActivityService.createUserActivity(users.get(), day, xp);
        }

        return ResponseEntity.ok(new CompletingLessonInfoResponse(
                xp,
                completed,
                mistakes
        ));
    }
}