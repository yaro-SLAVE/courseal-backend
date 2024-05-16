package online.courseal.courseal_backend.errors.exceptions;

import org.springframework.http.HttpStatus;

public class LessonNotFoundException extends ApplicationException {
    public LessonNotFoundException() {
        status = HttpStatus.NOT_FOUND;
        error = "lesson_not_found";
        errorMessage = "Lesson not found";
        description = "Lesson with this lesson_id not found";
    }
}
