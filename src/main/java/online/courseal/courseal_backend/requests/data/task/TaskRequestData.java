package online.courseal.courseal_backend.requests.data.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TaskRequestData {
    @JsonProperty("type")
    private String type;
    @JsonProperty("body")
    private List<TaskRequestBody> bodyList;
    @JsonProperty("options")
    private List<String> options;
    @JsonProperty("correct_option")
    private Integer correctOption;
}
