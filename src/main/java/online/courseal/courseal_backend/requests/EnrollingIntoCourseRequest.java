package online.courseal.courseal_backend.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class EnrollingIntoCourseRequest {
    @JsonProperty("course_id")
    private Integer courseId;
}
