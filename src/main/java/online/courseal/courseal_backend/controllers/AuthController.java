package online.courseal.courseal_backend.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import online.courseal.courseal_backend.configs.jwt.JwtUtils;
import online.courseal.courseal_backend.models.RefreshToken;
import online.courseal.courseal_backend.repositories.RefreshTokenRepository;
import online.courseal.courseal_backend.requests.LoginRequest;
import online.courseal.courseal_backend.repositories.UserRepository;
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
public class AuthController {
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
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

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

        jwtCookie.setPath("/jwt");
        jwtCookie.setMaxAge(86400);
        jwtCookie.setHttpOnly(true);

        tokenRefreshCookie.setPath("/refreshToken");
        tokenRefreshCookie.setMaxAge(86400);
        tokenRefreshCookie.setDomain("online.courseal");
        tokenRefreshCookie.setHttpOnly(true);

        response.setContentType("text/plain");
        response.addCookie(jwtCookie);
        response.addCookie(tokenRefreshCookie);
    }

    @GetMapping("/refresh")
    public String refreshToken(@CookieValue(value = "courseal_refresh", required = false) String tokenRefreshCookie, HttpServletResponse response){
        refreshTokenService.findByToken(tokenRefreshCookie)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String jwt = jwtUtils.generateTokenFromUsername(user.getUserName());
                    RefreshToken newRefreshToken = refreshTokenService.createRefreshToken(user.getUserId());
                    response.addCookie(new Cookie("courseal_jwt", jwt));
                    response.addCookie(new Cookie("courseal_refresh", newRefreshToken.getRefreshToken()));
                    RefreshToken refreshToken = refreshTokenService.findByToken(tokenRefreshCookie).get();
                    refreshToken.setValid(false);
                    refreshTokenRepository.save(refreshToken);
                    return null;
                });
        return null;
    }

    @GetMapping("/test")
    public String test(@CookieValue(value = "courseal_refresh", required = false) String data){
        return data;
    }
}
