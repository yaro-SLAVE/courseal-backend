package online.courseal.courseal_backend.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class CourseInfoResponse {
    @JsonProperty("course_name")
    private String courseName;
    @JsonProperty("course_description")
    private String courseDescription;
    @JsonProperty("votes")
    private Integer votes;
    @JsonProperty("last_updated_structure")
    private LocalDateTime lastUpdatedStructure;
    @JsonProperty("last_updated_lessons")
    private LocalDateTime lastUpdatedLessons;
    @JsonProperty("last_updated_tasks")
    private LocalDateTime lastUpdatedTasks;

}
