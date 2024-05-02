package online.courseal.courseal_backend.coursedata.enrolltaskcomplete;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import online.courseal.courseal_backend.coursedata.enrolltaskcomplete.data.TaskPracticeTrainingAnswer;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class EnrollTasksCompletePracticeTraining extends EnrollTasksComplete {
    private List<TaskPracticeTrainingAnswer> tasks;
}
