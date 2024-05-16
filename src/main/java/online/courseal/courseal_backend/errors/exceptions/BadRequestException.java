package online.courseal.courseal_backend.errors.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ApplicationException{
    public BadRequestException(){
        status = HttpStatus.BAD_REQUEST;
        error = "bad-request";
        errorMessage = "Bad request";
        description = "This is bad request";
    }
}
