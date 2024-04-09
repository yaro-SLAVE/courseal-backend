package online.courseal.courseal_backend.editorjs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import online.courseal.courseal_backend.editorjs.data.EditorJSParagraphData;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditorJSParagraph extends EditorJSBlock{
    private EditorJSParagraphData data;
}