package online.courseal.courseal_backend.errors.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidRefreshTokenException extends ApplicationException{
    public InvalidRefreshTokenException(){
        status = HttpStatus.FORBIDDEN;
        error = "refresh-invalid";
        errorMessage = "";
        description = "";
    }
}
