package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.errors.exceptions.BadRequestException;
import online.courseal.courseal_backend.models.Course;
import online.courseal.courseal_backend.models.CourseLesson;
import online.courseal.courseal_backend.models.User;
import online.courseal.courseal_backend.requests.CourseStructureUpdatingRequest;
import online.courseal.courseal_backend.requests.data.CourseStructureUpdatingData;
import online.courseal.courseal_backend.responses.CourseStructureListResponse;
import online.courseal.courseal_backend.responses.data.CourseStructureData;
import online.courseal.courseal_backend.services.CourseLessonService;
import online.courseal.courseal_backend.services.CourseService;
import online.courseal.courseal_backend.services.UserDetailsImpl;
import online.courseal.courseal_backend.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/course-management")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseStructureManagementController {
    @Autowired
    CourseService courseService;

    @Autowired
    UserDetailsServiceImpl userService;

    @Autowired
    CourseLessonService courseLessonService;

    @GetMapping("/{course_id}/structure")
    public ResponseEntity<?> getCourseStructure(@PathVariable("course_id") Integer courseId){
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

        ArrayList<CourseStructureListResponse> structureList = new ArrayList<>();

        if (!courses.get().getCourseLessons().isEmpty()) {
            Optional<CourseLesson> courseLessons = courses.get()
                    .getCourseLessons().stream()
                    .filter(c -> c.getLessonLevel() != null)
                    .max(Comparator.comparing(CourseLesson::getLessonLevel));

            if (!courseLessons.isEmpty()) {
                Integer maxLevel = courseLessons.get().getLessonLevel();

                for (int i = 0; i <= maxLevel; ++i) {
                    List<CourseLesson> courseLessons1 = courseLessonService.findByLessonLevelAndCourse(i, courses.get());
                    CourseStructureListResponse data = new CourseStructureListResponse();

                    for (CourseLesson courseLesson : courseLessons1.stream().toList()) {
                        data.add(new CourseStructureData(courseLesson.getCourseLessonId()));
                    }

                    structureList.add(data);
                }
            }
        }

        return ResponseEntity.ok(structureList);
    }

    @PutMapping("/{course_id}/structure")
    public HttpStatus updateCourseStructure(@RequestBody CourseStructureUpdatingRequest courseStructureUpdatingRequest, @PathVariable("course_id") Integer courseId){
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

        courses.get().setLastUpdatedLessons(LocalDateTime.now());
        courses.get().setLastUpdatedStructure(LocalDateTime.now());
        courseService.save(courses.get());

        for (CourseLesson courseLesson: courses.get().getCourseLessons()) {
            courseLesson.setLessonLevel(null);
            courseLessonService.save(courseLesson);
        }

        for (ArrayList<CourseStructureUpdatingData> list: courseStructureUpdatingRequest) {
            int index = courseStructureUpdatingRequest.indexOf(list);
            for (CourseStructureUpdatingData id: list) {
                Optional<CourseLesson> courseLessons = courseLessonService.findByCourseLessonId(id.getLessonId());

                if (!courseLessons.isEmpty() && courses.get().getCourseLessons().contains(courseLessons.get())) {
                    courseLessons.get().setLessonLevel(index);
                    courseLessons.get().setLastUpdated(LocalDateTime.now());
                    courseLessonService.save(courseLessons.get());
                }
            }
        }

        return HttpStatus.NO_CONTENT;
    }
}
