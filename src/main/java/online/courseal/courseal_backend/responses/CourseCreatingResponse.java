package online.courseal.courseal_backend.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CourseCreatingResponse {
    @JsonProperty("course_id")
    private Integer courseId;
}
