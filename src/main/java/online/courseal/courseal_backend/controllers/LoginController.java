package online.courseal.courseal_backend.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import online.courseal.courseal_backend.configs.jwt.JwtUtils;
import online.courseal.courseal_backend.models.RefreshToken;
import online.courseal.courseal_backend.requests.LoginRequest;
import online.courseal.courseal_backend.repository.UserRepository;
import online.courseal.courseal_backend.services.RefreshTokenService;
import online.courseal.courseal_backend.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public void authUser(HttpServletResponse response, @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsertag(),
                loginRequest.getPassword()));

        String jwt = jwtUtils.generateJwtToken(authentication);

        var jwtCookie = new Cookie("courseal_jwt", jwt);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUserId());

        var tokenRefreshCookie = new Cookie("courseal_refresh", refreshToken.getRefreshToken());

        response.addCookie(jwtCookie);
        response.addCookie(tokenRefreshCookie);
    }

    @PostMapping("/refresh")
    public void refreshToken(@Valid @CookieValue("courseal_refresh") String tokenRefreshCookie){
        /*
        return refreshTokenService
                .findByToken(tokenRefreshCookie)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser);
         */
    }
}
