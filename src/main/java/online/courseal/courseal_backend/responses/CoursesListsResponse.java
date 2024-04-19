package online.courseal.courseal_backend.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CoursesListsResponse {
    @JsonProperty("course_id")
    private Integer courseId;
    @JsonProperty("course_name")
    private String courseName;
    @JsonProperty("course_description")
    private String courseDescription;
    @JsonProperty("votes")
    private Integer votes;
}
