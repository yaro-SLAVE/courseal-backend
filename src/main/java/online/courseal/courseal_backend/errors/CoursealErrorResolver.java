package online.courseal.courseal_backend.errors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import online.courseal.courseal_backend.errors.exceptions.ApplicationException;
import online.courseal.courseal_backend.responses.records.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Component
public class CoursealErrorResolver extends AbstractHandlerExceptionResolver {
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        final ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());

        if (ex instanceof ApplicationException){
            modelAndView.setStatus(((ApplicationException) ex).getStatus());
            modelAndView.addObject(new ExceptionResponse(((ApplicationException) ex).getError(), ((ApplicationException) ex).getErrorMessage(), ((ApplicationException) ex).getDescription()));
            return modelAndView;
        }

        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        modelAndView.addObject(new ExceptionResponse("unknown", "unknown", "unknown"));
        return modelAndView;
    }
}
