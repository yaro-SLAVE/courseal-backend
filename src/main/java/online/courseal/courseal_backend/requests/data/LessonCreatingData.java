package online.courseal.courseal_backend.requests.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import online.courseal.courseal_backend.coursedata.lessons.CoursealLesson;
import online.courseal.courseal_backend.coursedata.lessons.CoursealLessonLecture;
import online.courseal.courseal_backend.models.enums.LessonType;

@Getter
public class LessonCreatingData {
    @Enumerated(EnumType.STRING)
    private LessonType type;
    @JsonProperty("lesson_content")
    private CoursealLesson lessonContent;
}
