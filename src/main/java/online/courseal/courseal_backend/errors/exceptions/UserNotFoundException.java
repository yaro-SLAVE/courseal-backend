package online.courseal.courseal_backend.errors.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ApplicationException {
    public UserNotFoundException() {
        status = HttpStatus.NOT_FOUND;
        error = "user-not-found";
        errorMessage = "User not found";
        description = "User not found";
    }
}
