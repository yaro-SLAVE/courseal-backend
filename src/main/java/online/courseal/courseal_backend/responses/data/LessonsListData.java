package online.courseal.courseal_backend.responses.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import online.courseal.courseal_backend.coursedata.lessons.CoursealLesson;
import online.courseal.courseal_backend.models.enums.LessonType;

@Data
@AllArgsConstructor
public class LessonsListData {
    @Enumerated(EnumType.STRING)
    private LessonType type;
    @JsonProperty("lesson_content")
    private CoursealLesson lessonContent;
}
