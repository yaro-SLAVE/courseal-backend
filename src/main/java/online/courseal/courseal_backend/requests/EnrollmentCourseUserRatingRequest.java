package online.courseal.courseal_backend.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class EnrollmentCourseUserRatingRequest {
    @JsonProperty("rating")
    private Integer rating;
}
