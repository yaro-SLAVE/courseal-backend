package online.courseal.courseal_backend.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import online.courseal.courseal_backend.coursedata.tasks.CoursealTask;

@AllArgsConstructor
public class TasksListResponse {
    @JsonProperty("task_id")
    private Integer taskId;
    @JsonProperty("task_name")
    private String taskName;
    @JsonProperty("task")
    private CoursealTask task;
}
