package online.courseal.courseal_backend.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import online.courseal.courseal_backend.responses.data.MaintainersData;

import java.util.List;

@AllArgsConstructor
public class CoursePublicInfoResponse {
    @JsonProperty("course_id")
    private Integer courseId;
    @JsonProperty("course_name")
    private String courseName;
    @JsonProperty("course_description")
    private String courseDescription;
    @JsonProperty("votes")
    private Integer votes;
    @JsonProperty("course_maintainers")
    List<MaintainersData> maintainersData;
}
