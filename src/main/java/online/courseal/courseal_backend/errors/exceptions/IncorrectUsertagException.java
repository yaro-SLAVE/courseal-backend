package online.courseal.courseal_backend.errors.exceptions;

import org.springframework.http.HttpStatus;

public class IncorrectUsertagException extends ApplicationException {
    public IncorrectUsertagException() {
        status = HttpStatus.BAD_REQUEST;
        error = "usertag-incorrect";
        errorMessage = "";
        description = "";
    }
}
