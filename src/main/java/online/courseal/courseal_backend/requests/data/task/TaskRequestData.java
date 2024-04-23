package online.courseal.courseal_backend.requests.data.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import online.courseal.courseal_backend.editorjs.EditorJSContent;

import java.util.List;

@Data
@AllArgsConstructor
public class TaskRequestData {
    @JsonProperty("type")
    private String type;
    @JsonProperty("body")
    private EditorJSContent body;
    @JsonProperty("options")
    private List<String> options;
    @JsonProperty("correct_option")
    private Integer correctOption;
}
