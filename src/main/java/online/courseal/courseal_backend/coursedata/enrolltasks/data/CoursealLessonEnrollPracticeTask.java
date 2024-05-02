package online.courseal.courseal_backend.coursedata.enrolltasks.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import online.courseal.courseal_backend.coursedata.tasks.CoursealTask;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursealLessonEnrollPracticeTask {
    @JsonProperty("task_id")
    private Integer taskId;
    private CoursealTask task;
}
