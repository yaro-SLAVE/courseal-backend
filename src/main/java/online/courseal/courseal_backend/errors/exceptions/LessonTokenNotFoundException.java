package online.courseal.courseal_backend.errors.exceptions;

import org.springframework.http.HttpStatus;

public class LessonTokenNotFoundException extends ApplicationException {
    public LessonTokenNotFoundException() {
        status = HttpStatus.NOT_FOUND;
        error = "lesson_token_not_found";
        errorMessage = "";
        description = "";
    }
}
