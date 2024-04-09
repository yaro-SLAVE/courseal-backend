package online.courseal.courseal_backend.editorjs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import online.courseal.courseal_backend.editorjs.data.EditorJSWarningData;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditorJSWarning extends EditorJSBlock{
    private EditorJSWarningData data;
}