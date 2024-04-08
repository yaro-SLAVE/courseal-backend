package online.courseal.courseal_backend.editorjs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import online.courseal.courseal_backend.editorjs.data.EditorJSLatexData;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditorJSLatex extends EditorJSBlock{
    private EditorJSLatexData data;
}
