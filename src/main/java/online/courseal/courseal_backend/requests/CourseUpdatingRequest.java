package online.courseal.courseal_backend.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CourseUpdatingRequest {
    @JsonProperty("course_name")
    private String courseName;
    @JsonProperty("course_description")
    private String courseDescription;
}
