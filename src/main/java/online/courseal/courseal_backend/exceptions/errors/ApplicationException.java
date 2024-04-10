package online.courseal.courseal_backend.exceptions.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class ApplicationException extends RuntimeException{
    protected HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    protected String error = "unknown";
    protected String errorMessage = "unknown";
    protected String description = "unknown";
}
