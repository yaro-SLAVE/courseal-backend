package online.courseal.courseal_backend.responses.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LessonMistakesData {
    @JsonProperty("lesson_id")
    private Integer lessonId;
    @JsonProperty("correct")
    private boolean correct;
}
