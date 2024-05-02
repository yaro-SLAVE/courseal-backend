package online.courseal.courseal_backend.coursedata.enrolltaskcomplete.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskPracticeTrainingAnswer {
    @JsonProperty("task_id")
    private Integer taskId;
    private Boolean correct;
}
