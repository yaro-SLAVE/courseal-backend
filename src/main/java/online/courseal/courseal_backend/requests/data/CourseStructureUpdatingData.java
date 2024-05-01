package online.courseal.courseal_backend.requests.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CourseStructureUpdatingData {
    @JsonProperty("lesson_id")
    private Integer lessonId;
}
