package online.courseal.courseal_backend.configs.editorjs.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditorJSImageData {
    private EditorJSImageFile file;
    private String caption;
    private Boolean withBorder;
    private Boolean withBackGround;
    private Boolean stretched;
}
