package online.courseal.courseal_backend.errors.exceptions;

import org.springframework.http.HttpStatus;

public class CantCreateCoursesException extends ApplicationException{
    public CantCreateCoursesException(){
        status = HttpStatus.FORBIDDEN;
        error = "cant-create-courses";
        errorMessage = "";
        description = "";
    }
}
