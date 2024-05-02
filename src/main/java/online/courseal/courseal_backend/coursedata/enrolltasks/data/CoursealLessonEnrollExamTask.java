package online.courseal.courseal_backend.coursedata.enrolltasks.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import online.courseal.courseal_backend.coursedata.examtasks.CoursealExamTask;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoursealLessonEnrollExamTask {
    @JsonProperty("task_id")
    private Integer taskId;
    @JsonProperty("task")
    private CoursealExamTask task;
}
