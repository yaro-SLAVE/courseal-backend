package online.courseal.courseal_backend.configs.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import online.courseal.courseal_backend.errors.exceptions.ApplicationException;
import online.courseal.courseal_backend.errors.exceptions.InvalidJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        InvalidJwtException exception = new InvalidJwtException();

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(exception.getStatus().value());

        final Map<String, Object> body = new HashMap<>();
        body.put("error", exception.getError());
        body.put("message", exception.getErrorMessage());
        body.put("description", exception.getDescription());

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }
}
