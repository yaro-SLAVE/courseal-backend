package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.errors.exceptions.UserNotFoundException;
import online.courseal.courseal_backend.models.CourseEnrollment;
import online.courseal.courseal_backend.models.CourseMaintainer;
import online.courseal.courseal_backend.models.User;
import online.courseal.courseal_backend.models.UserActivity;
import online.courseal.courseal_backend.repositories.UserRepository;
import online.courseal.courseal_backend.responses.UserActivityResponse;
import online.courseal.courseal_backend.responses.UserInfoResponse;
import online.courseal.courseal_backend.responses.UsersListResponse;
import online.courseal.courseal_backend.responses.data.CoursesEnrollmentsData;
import online.courseal.courseal_backend.responses.data.CoursesMaintainersData;
import online.courseal.courseal_backend.responses.data.UserActivityData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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

    @GetMapping
    public ResponseEntity<?> getUsersList() {
        List<User> users = userRepository.findAll();

        ArrayList<UsersListResponse> usersListResponse = new ArrayList<>();

        for (User user: users) {
            int xp = 0;
            for (UserActivity userActivity: user.getUserActivities()) {
                xp += userActivity.getXp();
            }

            usersListResponse.add(new UsersListResponse(
                    user.getUserTag(),
                    user.getUserName(),
                    xp
            ));
        }

        for (int i = 0; i < usersListResponse.size() - 1; ++i){
            for (int j = 1; j < usersListResponse.size(); ++j) {
                if (usersListResponse.get(j).getXp() > usersListResponse.get(i).getXp()) {
                    UsersListResponse temp_var = usersListResponse.get(i);
                    usersListResponse.add(i, usersListResponse.get(j));
                    usersListResponse.add(j, temp_var);
                }
            }
        }

        return ResponseEntity.ok(usersListResponse);
    }

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
                    courseEnrollment.getCourse().getCourseName(),
                    courseEnrollment.getXp()
            ));
        }

        ArrayList<CoursesMaintainersData> coursesMaintainersDataList = new ArrayList<>();
        for (CourseMaintainer courseMaintainer: users.get().getCourseMaintainers()) {
            coursesMaintainersDataList.add(new CoursesMaintainersData(
                    courseMaintainer.getCourse().getCourseId(),
                    courseMaintainer.getCourse().getCourseName(),
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
    public ResponseEntity<?> getUserActivity(@PathVariable("usertag") String userTag){
        Optional<User> users = userRepository.findByUserTag(userTag);

        if (users.isEmpty()){
            throw new UserNotFoundException();
        }

        ArrayList<UserActivityData> userActivityDataList = new ArrayList<>();

        for (UserActivity userActivity: users.get().getUserActivities()) {
            userActivityDataList.add(new UserActivityData(
                    userActivity.getDay(),
                    userActivity.getXp()
            ));
        }

        return ResponseEntity.ok(new UserActivityResponse(
                users.get().getUserTag(),
                userActivityDataList
        ));
    }
}
