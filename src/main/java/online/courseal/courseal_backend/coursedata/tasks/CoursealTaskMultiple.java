package online.courseal.courseal_backend.coursedata.tasks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import online.courseal.courseal_backend.coursedata.editorjs.EditorJSContent;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class CoursealTaskMultiple extends CoursealTask{
    @JsonProperty("body")
    private EditorJSContent body;
    @JsonProperty("option")
    private List<TaskMultipleOption> options;
}
