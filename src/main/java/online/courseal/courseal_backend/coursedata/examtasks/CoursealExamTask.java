package online.courseal.courseal_backend.coursedata.examtasks;

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
        @JsonSubTypes.Type(value = CoursealExamTaskMultiple.class, name = "test_multiple_answers"),
        @JsonSubTypes.Type(value = CoursealExamTaskSingle.class, name = "test_single_answer")

})
public abstract sealed class CoursealExamTask
        permits CoursealExamTaskSingle, CoursealExamTaskMultiple {
}
