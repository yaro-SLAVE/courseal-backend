package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.configs.jwt.JwtUtils;
import online.courseal.courseal_backend.models.User;
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
@RequestMapping("/api/user/register")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RegisterController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping
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
}
