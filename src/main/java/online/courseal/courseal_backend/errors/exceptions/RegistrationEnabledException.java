package online.courseal.courseal_backend.errors.exceptions;

import org.springframework.http.HttpStatus;

public class RegistrationEnabledException extends ApplicationException {
    public RegistrationEnabledException() {
        status = HttpStatus.FORBIDDEN;
        error = "Forbidden";
        errorMessage = "Registration of new accounts is disabled";
        description = "";
    }
}
