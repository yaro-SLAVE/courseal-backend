package online.courseal.courseal_backend.coursedata.lessons;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class CoursealLessonTraining extends CoursealLesson {
}
