package online.courseal.courseal_backend.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import online.courseal.courseal_backend.coursedata.enrolltasks.EnrollTask;

@AllArgsConstructor
public class EnrollmentCourseGettingTasksResponse {
    @JsonProperty("lesson_token")
    private String lessonToken;
    @JsonProperty("tasks")
    private EnrollTask tasks;
}
