package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.models.User;
import online.courseal.courseal_backend.requests.ChangeNameRequest;
import online.courseal.courseal_backend.requests.ChangePasswordRequest;
import online.courseal.courseal_backend.responses.MessageResponse;
import online.courseal.courseal_backend.requests.RegisterRequest;
import online.courseal.courseal_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
        if (userRepository.existsByUserTag(registerRequest.getUsertag())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse());
        }

        User user = new User(registerRequest.getUsertag(),
                registerRequest.getUsername(),
                passwordEncoder.encode(registerRequest.getPassword()),
                LocalDateTime.now());

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse());
    }

    @PostMapping("/change-name")
    public ResponseEntity<?> changeName(@RequestBody ChangeNameRequest changeNameRequest, @CookieValue(name = "courseal_jwt", required = false) String jwt){
        return null;
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest, @CookieValue(name = "courseal_jwt", required = false) String jwt){
        return null;
    }
}
