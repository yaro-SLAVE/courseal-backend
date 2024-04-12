package online.courseal.courseal_backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @GetMapping("/{usertag}")
    public ResponseEntity<?> getUserInfo(){
        return null;
    }

    @GetMapping("/{usertag}/activity")
    public ResponseEntity<?> getUserActivity(){
        return null;
    }
}
