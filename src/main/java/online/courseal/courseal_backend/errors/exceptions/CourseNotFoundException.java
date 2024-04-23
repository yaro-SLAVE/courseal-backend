package online.courseal.courseal_backend.errors.exceptions;

import org.springframework.http.HttpStatus;

public class CourseNotFoundException extends ApplicationException {
    public CourseNotFoundException() {
        status = HttpStatus.NOT_FOUND;
        error = "course-not-found";
        errorMessage = "";
        description = "";
    }
}
