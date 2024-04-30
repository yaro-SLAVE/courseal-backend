package online.courseal.courseal_backend.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import online.courseal.courseal_backend.coursedata.lessons.CoursealLesson;

@AllArgsConstructor
public class LessonsListResponse {
    @JsonProperty("lesson_id")
    private Integer lessonId;
    @JsonProperty("lesson_name")
    private String lessonName;
    @JsonProperty("lesson_progress_needed")
    private Integer lessonProgressNeeded;
    @JsonProperty("lesson")
    private CoursealLesson lesson;
}
