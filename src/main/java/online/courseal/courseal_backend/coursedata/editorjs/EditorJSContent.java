package online.courseal.courseal_backend.coursedata.editorjs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditorJSContent {
    private Long time;
    @NotNull
    private List<EditorJSBlock> blocks;
    private String version;
}
