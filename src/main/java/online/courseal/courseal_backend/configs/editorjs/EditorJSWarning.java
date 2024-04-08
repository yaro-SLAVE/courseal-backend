package online.courseal.courseal_backend.configs.editorjs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import online.courseal.courseal_backend.configs.editorjs.data.EditorJSWarningData;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditorJSWarning extends EditorJSBlock{
    private EditorJSWarningData data;
}
