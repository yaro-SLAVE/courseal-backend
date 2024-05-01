package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.errors.exceptions.BadRequestException;
import online.courseal.courseal_backend.models.Course;
import online.courseal.courseal_backend.models.CourseLesson;
import online.courseal.courseal_backend.models.User;
import online.courseal.courseal_backend.requests.CourseStructureUpdatingRequest;
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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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

        Optional<CourseLesson> courseLessons = courses.get().getCourseLessons().stream().max(Comparator.comparing(CourseLesson::getLessonLevel));
        Integer maxLevel = courseLessons.get().getLessonLevel();

        if (maxLevel != null) {
            for (int i = 0; i <= maxLevel; ++i) {
                courseLessons = courseLessonService.findByLessonLevelAndCourse(i, courses.get());
                ArrayList<CourseStructureData> data = new ArrayList<>();

                for (CourseLesson courseLesson : courseLessons.stream().toList()) {
                    data.add(new CourseStructureData(courseLesson.getCourseLessonId()));
                }

                structureList.add(new CourseStructureListResponse(data));
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

        return HttpStatus.NO_CONTENT;
    }
}
