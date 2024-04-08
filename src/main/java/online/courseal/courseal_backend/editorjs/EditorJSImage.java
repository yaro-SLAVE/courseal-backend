package online.courseal.courseal_backend.editorjs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import online.courseal.courseal_backend.editorjs.data.EditorJSImageData;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditorJSImage extends EditorJSBlock{
    private EditorJSImageData data;
}
