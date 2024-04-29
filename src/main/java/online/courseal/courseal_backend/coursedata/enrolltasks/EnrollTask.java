package online.courseal.courseal_backend.coursedata.enrolltasks;

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
        @JsonSubTypes.Type(value = EnrollTaskExam.class, name = "exam"),
        @JsonSubTypes.Type(value = EnrollTaskLecture.class, name = "lecture"),
        @JsonSubTypes.Type(value = EnrollTaskPracticeTraining.class, name = "practice_training")
})
public abstract sealed class EnrollTask
        permits EnrollTaskExam, EnrollTaskLecture, EnrollTaskPracticeTraining {
}
