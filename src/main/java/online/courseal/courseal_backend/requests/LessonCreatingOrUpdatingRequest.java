package online.courseal.courseal_backend.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import online.courseal.courseal_backend.coursedata.lessons.CoursealLesson;

@Getter
public class LessonCreatingOrUpdatingRequest {
    @JsonProperty("lesson_name")
    private String lessonName;
    @JsonProperty("lesson_progress_needed")
    private Integer lessonProgressNeeded;
    @JsonProperty("lesson")
    private CoursealLesson lesson;
}
