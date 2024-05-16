package online.courseal.courseal_backend.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EnrollmentCourseUserRatingResponse {
    @JsonProperty("rating")
    private Integer rating;
}
