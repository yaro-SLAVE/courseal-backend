package online.courseal.courseal_backend.requests.data.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskRequestBodyData {
    @JsonProperty("text")
    private String text;
}
