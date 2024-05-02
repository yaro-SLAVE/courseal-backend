package online.courseal.courseal_backend.coursedata.lessons;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", include = JsonTypeInfo.As.EXTERNAL_PROPERTY)
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = CoursealLessonExam.class, name = "exam"),
        @JsonSubTypes.Type(value = CoursealLessonLecture.class, name = "lecture"),
        @JsonSubTypes.Type(value = CoursealLessonPractice.class, name = "practice"),
        @JsonSubTypes.Type(value = CoursealLessonTraining.class, name = "training")
})
public abstract sealed class CoursealLesson
        permits CoursealLessonExam, CoursealLessonLecture, CoursealLessonPractice, CoursealLessonTraining {
}
