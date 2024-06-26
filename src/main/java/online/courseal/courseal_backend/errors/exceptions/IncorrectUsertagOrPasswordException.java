package online.courseal.courseal_backend.errors.exceptions;

import org.springframework.http.HttpStatus;

public class IncorrectUsertagOrPasswordException extends ApplicationException{
    public IncorrectUsertagOrPasswordException(){
        status = HttpStatus.FORBIDDEN;
        error = "usertag-or-password-incorrect";
        errorMessage = "Incorrect usertag or password";
        description = "You input incorrect usertag or password";
    }
}
