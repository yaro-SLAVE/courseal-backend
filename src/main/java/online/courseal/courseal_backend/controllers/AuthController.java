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
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${JWT_EXPIRATION_MS}")
    private Long jwtExpirationMs;
    @Value("${REFRESH_EXPIRATION_MS}")
    private Long refreshTokenDurationMs;

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

        jwtCookie.setPath("/api");
        jwtCookie.setMaxAge((int)(jwtExpirationMs / 1000));
        jwtCookie.setHttpOnly(true);

        tokenRefreshCookie.setPath("/api/auth");
        tokenRefreshCookie.setMaxAge((int)(refreshTokenDurationMs / 1000));
        tokenRefreshCookie.setHttpOnly(true);

        response.addCookie(jwtCookie);
        response.addCookie(tokenRefreshCookie);
    }

    @GetMapping("/refresh")
    public String refreshToken(@CookieValue(value = "courseal_refresh", required = false) String tokenRefreshCookie, HttpServletResponse response){
        refreshTokenService.findByToken(tokenRefreshCookie)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String jwt = jwtUtils.generateTokenFromUserTag(user.getUserTag());
                    RefreshToken newRefreshToken = refreshTokenService.createRefreshToken(user.getUserId());
                    var jwtCookie = new Cookie("courseal_jwt", jwt);
                    var refreshCookie = new Cookie("courseal_refresh", newRefreshToken.getRefreshToken());

                    jwtCookie.setPath("/api");
                    jwtCookie.setMaxAge((int)(jwtExpirationMs / 1000));
                    jwtCookie.setHttpOnly(true);

                    refreshCookie.setPath("/api/auth");
                    refreshCookie.setMaxAge((int)(refreshTokenDurationMs / 1000));
                    refreshCookie.setHttpOnly(true);

                    response.addCookie(jwtCookie);
                    response.addCookie(refreshCookie);
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
