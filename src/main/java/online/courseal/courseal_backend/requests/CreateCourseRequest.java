package online.courseal.courseal_backend.requests;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class CreateCourseRequest {
    private String course_name;
    private String course_description;
}
