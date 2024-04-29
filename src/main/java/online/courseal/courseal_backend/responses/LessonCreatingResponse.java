package online.courseal.courseal_backend.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LessonCreatingResponse {
    @JsonProperty("lesson_id")
    private Integer lessonId;
}
