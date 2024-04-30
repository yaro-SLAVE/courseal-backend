package online.courseal.courseal_backend.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import online.courseal.courseal_backend.responses.data.LessonsListData;

@AllArgsConstructor
public class LessonsListResponse {
    @JsonProperty("lesson_name")
    private String lessonName;
    @JsonProperty("lesson_progress_needed")
    private Integer lessonProgressNeeded;
    @JsonProperty("lesson")
    private LessonsListData lesson;
}
