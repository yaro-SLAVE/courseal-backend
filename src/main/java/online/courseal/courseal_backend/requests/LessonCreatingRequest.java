package online.courseal.courseal_backend.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import online.courseal.courseal_backend.requests.data.LessonCreatingData;

@Getter
public class LessonCreatingRequest {
    @JsonProperty("lesson_name")
    private String lessonName;
    @JsonProperty("lesson_progress_needed")
    private Integer lessonProgressNeeded;
    @JsonProperty("lesson")
    private LessonCreatingData lesson;
}
