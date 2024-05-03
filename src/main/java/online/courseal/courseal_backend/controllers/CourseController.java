package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.errors.exceptions.CourseNotFoundException;
import online.courseal.courseal_backend.models.Course;
import online.courseal.courseal_backend.models.CourseEnrollment;
import online.courseal.courseal_backend.models.CourseMaintainer;
import online.courseal.courseal_backend.responses.CoursePublicInfoResponse;
import online.courseal.courseal_backend.responses.CoursesListResponse;
import online.courseal.courseal_backend.responses.data.MaintainersData;
import online.courseal.courseal_backend.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/course")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping
    public ResponseEntity<?> getCoursesLists(@RequestParam(value = "search", required = false) String search){
        List<Course> courses;

        if (search == null) {
            courses = courseService.findAll();
        } else {
            courses = courseService.findByCourseNameLike(search);
        }

        ArrayList<CoursesListResponse> response = new ArrayList<>();

        for (Course course: courses){
            int votes = 0;

            for (CourseEnrollment courseEnrollment: course.getCourseEnrollments()){
                votes += courseEnrollment.getRating();
            }

            response.add(new CoursesListResponse(
                    course.getCourseId(),
                    course.getCourseName(),
                    course.getCourseDescription(),
                    votes
            ));
        }

        response.sort(Comparator.comparingInt(CoursesListResponse::getVotes));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{course_id}")
    public ResponseEntity<?> getCoursePublicInfo(@PathVariable("course_id") Integer courseId) {
        Optional<Course> courses = courseService.findByCourseId(courseId);

        if (courses.isEmpty()) {
            throw new CourseNotFoundException();
        }

        int votes = 0;

        for (CourseEnrollment courseEnrollment: courses.get().getCourseEnrollments()){
            votes += courseEnrollment.getRating();
        }

        ArrayList<MaintainersData> maintainersData = new ArrayList<>();

        for (CourseMaintainer courseMaintainer: courses.get().getCourseMaintainers()) {
            maintainersData.add(new MaintainersData(
                    courseMaintainer.getUser().getUserTag(),
                    courseMaintainer.getPermissions()
            ));
        }

        return ResponseEntity.ok(new CoursePublicInfoResponse(
                courseId,
                courses.get().getCourseName(),
                courses.get().getCourseDescription(),
                votes,
                maintainersData
        ));
    }
}

