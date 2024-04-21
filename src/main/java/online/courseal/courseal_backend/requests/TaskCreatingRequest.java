package online.courseal.courseal_backend.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import online.courseal.courseal_backend.requests.data.task.TaskRequestData;

import java.util.List;

@Getter
public class TaskCreatingRequest {
    @JsonProperty("task_name")
    private String taskName;
    @JsonProperty("task")
    private List<TaskRequestData> dataList;
}
