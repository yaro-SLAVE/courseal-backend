package online.courseal.courseal_backend.editorjs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import online.courseal.courseal_backend.editorjs.data.EditorJSHeaderData;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditorJSHeader extends EditorJSBlock{
    private EditorJSHeaderData data;
}