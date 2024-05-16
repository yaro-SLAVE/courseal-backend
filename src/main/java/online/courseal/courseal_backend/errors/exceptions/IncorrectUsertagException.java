package online.courseal.courseal_backend.errors.exceptions;

import org.springframework.http.HttpStatus;

public class IncorrectUsertagException extends ApplicationException {
    public IncorrectUsertagException() {
        status = HttpStatus.BAD_REQUEST;
        error = "usertag-incorrect";
        errorMessage = "Usertag is incorrect";
        description = "This usertag is incorrect";
    }
}
