package online.courseal.courseal_backend.configs.editorjs.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditorJSImageFile {
    private String url;
}
