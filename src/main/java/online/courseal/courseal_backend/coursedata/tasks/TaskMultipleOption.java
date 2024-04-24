package online.courseal.courseal_backend.coursedata.tasks;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskMultipleOption {
    @JsonProperty("text")
    private String text;
    @JsonProperty("is_correct")
    private Boolean isCorrect;
}
