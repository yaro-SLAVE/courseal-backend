package online.courseal.courseal_backend.coursedata.tasks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import online.courseal.courseal_backend.coursedata.editorjs.EditorJSContent;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("test_multiple_answers")
public final class CoursealTaskMultiple extends CoursealTask{
    @JsonProperty("body")
    private EditorJSContent body;
    @JsonProperty("option")
    private List<TaskMultipleOption> options;
}
