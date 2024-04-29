package online.courseal.courseal_backend.coursedata.examtasks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import online.courseal.courseal_backend.coursedata.editorjs.EditorJSContent;
import online.courseal.courseal_backend.coursedata.examtasks.data.ExamTaskMultipleOption;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class CoursealExamTaskMultiple extends CoursealExamTask {
    private EditorJSContent body;
    private List<ExamTaskMultipleOption> options;
}
