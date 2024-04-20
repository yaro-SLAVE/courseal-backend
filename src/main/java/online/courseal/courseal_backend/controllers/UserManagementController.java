package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.configs.ServerConfig;
import online.courseal.courseal_backend.errors.exceptions.IncorrectUsertagException;
import online.courseal.courseal_backend.errors.exceptions.RegistrationEnabledException;
import online.courseal.courseal_backend.errors.exceptions.UserNotFoundException;
import online.courseal.courseal_backend.models.CourseEnrollment;
import online.courseal.courseal_backend.models.CourseMaintainer;
import online.courseal.courseal_backend.models.User;
import online.courseal.courseal_backend.requests.ChangingNameRequest;
import online.courseal.courseal_backend.requests.ChangingPasswordRequest;
import online.courseal.courseal_backend.responses.MessageResponse;
import online.courseal.courseal_backend.requests.RegisterRequest;
import online.courseal.courseal_backend.repositories.UserRepository;
import online.courseal.courseal_backend.responses.UserInfoResponse;
import online.courseal.courseal_backend.responses.data.CoursesEnrollmentsData;
import online.courseal.courseal_backend.responses.data.CoursesMaintainersData;
import online.courseal.courseal_backend.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/user-management")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserManagementController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ServerConfig serverConfig;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        if (!serverConfig.getServerInfo().getServerRegistrationEnabled()){
            throw new RegistrationEnabledException();
        }

        Pattern pattern = Pattern.compile("[a-z0-9-_.]+");
        Matcher matcher = pattern.matcher(registerRequest.getUsertag());

        if (!matcher.matches()){
            throw new IncorrectUsertagException();
        }

        Boolean canCreateCourses = serverConfig.getServerInfo().getDefaultCanCreateCourses();

        if (userRepository.existsByUserTag(registerRequest.getUsertag())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse());
        }

        User user = new User(registerRequest.getUsertag(),
                registerRequest.getUsername(),
                passwordEncoder.encode(registerRequest.getPassword()),
                LocalDateTime.now(),
                canCreateCourses);

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse());
    }

    @GetMapping
    public ResponseEntity<?> getInfo(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> users = userRepository.findByUserTag(userDetails.getUserTag());

        int xp = 0;
        for (CourseEnrollment courseEnrollment: users.get().getCourseEnrollments()) {
            xp += courseEnrollment.getXp();
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

    @PutMapping("/username")
    public ResponseEntity<?> changeName(@RequestBody ChangingNameRequest changingNameRequest){
        return null;
    }

    @PutMapping("/password")
    public ResponseEntity<?> changePassword(@RequestBody ChangingPasswordRequest changingPasswordRequest){
        return null;
    }
}
