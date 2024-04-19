package online.courseal.courseal_backend.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseCreatingRequest {
    @JsonProperty("course_name")
    private String courseName;
    @JsonProperty("course_description")
    private String courseDescription;
}
