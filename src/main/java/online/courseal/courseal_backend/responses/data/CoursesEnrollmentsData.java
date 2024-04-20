package online.courseal.courseal_backend.responses.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoursesEnrollmentsData {
    @JsonProperty("course_id")
    private Integer courseId;
    @JsonProperty("xp")
    private Integer xp;
}
