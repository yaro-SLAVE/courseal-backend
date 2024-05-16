package online.courseal.courseal_backend.errors.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidRefreshTokenException extends ApplicationException{
    public InvalidRefreshTokenException(){
        status = HttpStatus.FORBIDDEN;
        error = "refresh-invalid";
        errorMessage = "Provided Refresh is invalid";
        description = "The provided Refresh either has expired or its cryptographic signature doesn't match the server's private key";
    }
}
