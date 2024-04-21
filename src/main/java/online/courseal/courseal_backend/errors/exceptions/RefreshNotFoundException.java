package online.courseal.courseal_backend.errors.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RefreshNotFoundException extends ApplicationException {
    public RefreshNotFoundException() {
        status = HttpStatus.NOT_FOUND;
        error = "refresh-not-found";
        errorMessage = "";
        description = "";
    }
}
