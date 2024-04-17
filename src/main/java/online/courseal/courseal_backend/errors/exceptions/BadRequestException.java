package online.courseal.courseal_backend.errors.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ApplicationException{
    public BadRequestException(){
        status = HttpStatus.BAD_REQUEST;
        error = "jwt-invalid";
        errorMessage = "Provided JWT is invalid";
        description = "The provided JWT either has expired or its cryptographic signature doesn't match the server's private key";
    }
}
