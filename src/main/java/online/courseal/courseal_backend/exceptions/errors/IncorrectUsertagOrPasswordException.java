package online.courseal.courseal_backend.exceptions.errors;

import org.springframework.http.HttpStatus;

public class IncorrectUsertagOrPasswordException extends ApplicationException{
    public IncorrectUsertagOrPasswordException(){
        status = HttpStatus.FORBIDDEN;
        error = "jwt-invalid";
        errorMessage = "Provided JWT is invalid";
        description = "The provided JWT either has expired or its cryptographic signature doesn't match the server's private key";
    }
}
