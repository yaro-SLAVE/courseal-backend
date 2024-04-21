package online.courseal.courseal_backend.errors.exceptions;

import org.springframework.http.HttpStatus;

public class AccountAlreadyExistsException extends ApplicationException{
    public AccountAlreadyExistsException(){
        status = HttpStatus.CONFLICT;
        error = "account-already-exist";
        errorMessage = "";
        description = "";
    }
}
