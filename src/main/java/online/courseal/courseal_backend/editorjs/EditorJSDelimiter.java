package online.courseal.courseal_backend.editorjs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditorJSDelimiter extends EditorJSBlock{
    private Unit data;
}