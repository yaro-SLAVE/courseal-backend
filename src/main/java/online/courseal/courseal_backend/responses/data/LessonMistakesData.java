package online.courseal.courseal_backend.responses.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LessonMistakesData {
    @JsonProperty("task_id")
    private Integer taskId;
    @JsonProperty("correct")
    private boolean correct;
}
