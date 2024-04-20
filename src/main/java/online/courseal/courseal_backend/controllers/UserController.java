package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.errors.exceptions.UserNotFoundException;
import online.courseal.courseal_backend.models.CourseEnrollment;
import online.courseal.courseal_backend.models.CourseMaintainer;
import online.courseal.courseal_backend.models.User;
import online.courseal.courseal_backend.models.UserActivity;
import online.courseal.courseal_backend.repositories.UserRepository;
import online.courseal.courseal_backend.responses.UserInfoResponse;
import online.courseal.courseal_backend.responses.data.CoursesEnrollmentsData;
import online.courseal.courseal_backend.responses.data.CoursesMaintainersData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/{usertag}")
    public ResponseEntity<?> getUserInfo(@PathVariable("usertag") String userTag){
        Optional<User> users = userRepository.findByUserTag(userTag);

        if (users.isEmpty()){
            throw new UserNotFoundException();
        }

        int xp = 0;
        for (UserActivity userActivity: users.get().getUserActivities()) {
            xp += userActivity.getXp();
        }

        ArrayList<CoursesEnrollmentsData> coursesEnrollmentsDataList = new ArrayList<>();
        for (CourseEnrollment courseEnrollment: users.get().getCourseEnrollments()) {
            coursesEnrollmentsDataList.add(new CoursesEnrollmentsData(
                    courseEnrollment.getCourse().getCourseId(),
                    courseEnrollment.getXp()
            ));
        }

        ArrayList<CoursesMaintainersData> coursesMaintainersDataList = new ArrayList<>();
        for (CourseMaintainer courseMaintainer: users.get().getCourseMaintainers()) {
            coursesMaintainersDataList.add(new CoursesMaintainersData(
                    courseMaintainer.getCourse().getCourseId(),
                    courseMaintainer.getPermissions()
            ));
        }

        return ResponseEntity.ok(new UserInfoResponse(
                users.get().getUserTag(),
                users.get().getUserName(),
                users.get().getDateCreated(),
                users.get().isCanCreate(),
                xp,
                "",
                coursesEnrollmentsDataList,
                coursesMaintainersDataList
        ));
    }

    @GetMapping("/{usertag}/activity")
    public ResponseEntity<?> getUserActivity(){
        return null;
    }
}
