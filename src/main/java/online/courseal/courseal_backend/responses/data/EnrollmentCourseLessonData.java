package online.courseal.courseal_backend.responses.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnrollmentCourseLessonData {
    @JsonProperty("lesson_id")
    private Integer lessonId;
    @JsonProperty("lesson_name")
    private String lessonName;
    @JsonProperty("lesson_progress")
    private Integer lessonProgress;
    @JsonProperty("lesson_progress_needed")
    private Integer lessonProgressNeeded;
    @JsonProperty("can_be_done")
    private Boolean canBeDone;
}
