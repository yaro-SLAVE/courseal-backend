package online.courseal.courseal_backend.editorjs.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditorJSParagraphData {
    private String text;
}