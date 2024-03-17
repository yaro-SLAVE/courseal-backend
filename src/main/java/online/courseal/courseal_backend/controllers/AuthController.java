package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.configs.jwt.JwtUtils;
import online.courseal.courseal_backend.models.User;
import online.courseal.courseal_backend.pojo.JwtResponse;
import online.courseal.courseal_backend.pojo.LoginRequest;
import online.courseal.courseal_backend.pojo.MessageResponse;
import online.courseal.courseal_backend.pojo.SignupRequest;
import online.courseal.courseal_backend.repository.UserRepository;
import online.courseal.courseal_backend.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUserTag(),
                loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUserId(),
                userDetails.getUsername(),
                userDetails.getUserTag(),
                userDetails.getEmail()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest){
        if (userRepository.existsByUserTag(signupRequest.getUserTag())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse());
        }

        if (userRepository.existsByUserName(signupRequest.getUserName())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse());
        }

        User user = new User(signupRequest.getUserTag(),
                signupRequest.getUserName(),
                passwordEncoder.encode(signupRequest.getPassword()));

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse());
    }
}
