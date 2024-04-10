package online.courseal.courseal_backend.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import online.courseal.courseal_backend.responses.records.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Component
public class CoursealExceptionResolver extends AbstractHandlerExceptionResolver {
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        final ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
        if (ex instanceof InvalidTokenException){
            final String error = "jwt-invalid";
            final String message = "Provided JWT is invalid";
            final String description = "The provided JWT either has expired or its cryptographic signature doesn't match the server's private key";
            modelAndView.setStatus(HttpStatus.FORBIDDEN);
            modelAndView.addObject(new ExceptionResponse(error, message, description));
            return modelAndView;
        } else if (ex instanceof IncorrectUsertagOrPasswordException){
            final String error = "jwt-invalid";
            final String message = "Provided JWT is invalid";
            final String description = "The provided JWT either has expired or its cryptographic signature doesn't match the server's private key";
            modelAndView.setStatus(HttpStatus.FORBIDDEN);
            modelAndView.addObject(new ExceptionResponse(error, message, description));
            return modelAndView;
        } else if (ex instanceof BadRequestException){
            final String error = "jwt-invalid";
            final String message = "Provided JWT is invalid";
            final String description = "The provided JWT either has expired or its cryptographic signature doesn't match the server's private key";
            modelAndView.setStatus(HttpStatus.BAD_REQUEST);
            modelAndView.addObject(new ExceptionResponse(error, message, description));
            return modelAndView;
        } else if (ex instanceof AccountAlreadyExistsException){
            final String error = "jwt-invalid";
            final String message = "Provided JWT is invalid";
            final String description = "The provided JWT either has expired or its cryptographic signature doesn't match the server's private key";
            modelAndView.setStatus(HttpStatus.CONFLICT);
            modelAndView.addObject(new ExceptionResponse(error, message, description));
            return modelAndView;
        } else if (ex instanceof CantCreateCoursesException){
            final String error = "jwt-invalid";
            final String message = "Provided JWT is invalid";
            final String description = "The provided JWT either has expired or its cryptographic signature doesn't match the server's private key";
            modelAndView.setStatus(HttpStatus.FORBIDDEN);
            modelAndView.addObject(new ExceptionResponse(error, message, description));
            return modelAndView;
        }

        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        modelAndView.addObject("message", "При выполнении запроса произошла ошибка");
        return modelAndView;
    }
}
