package online.courseal.courseal_backend.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import online.courseal.courseal_backend.coursedata.enrolltaskcomplete.EnrollTasksComplete;
import online.courseal.courseal_backend.coursedata.tasks.CoursealTask;

import java.util.TimeZone;

@Getter
public class LessonCompletingRequest {
    @JsonProperty("lesson_token")
    private String lessonToken;
    @JsonProperty("timezone")
    private TimeZone timeZone;
    @JsonProperty("results")
    private EnrollTasksComplete task;
}
