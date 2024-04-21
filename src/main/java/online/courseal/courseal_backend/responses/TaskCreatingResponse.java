package online.courseal.courseal_backend.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TaskCreatingResponse {
    @JsonProperty("task_id")
    private Integer taskId;
}
