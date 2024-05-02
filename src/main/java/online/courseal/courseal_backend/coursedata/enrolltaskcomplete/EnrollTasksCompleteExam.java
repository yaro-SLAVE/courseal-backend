package online.courseal.courseal_backend.coursedata.enrolltaskcomplete;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import online.courseal.courseal_backend.coursedata.enrolltaskcomplete.data.TaskExamAnswer;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class EnrollTasksCompleteExam extends EnrollTasksComplete {
    private List<TaskExamAnswer> tasks;
}
