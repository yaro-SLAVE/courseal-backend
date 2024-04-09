package online.courseal.courseal_backend.editorjs.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditorJSImageData {
    private EditorJSImageFile file;
    private String caption;
    private Boolean withBorder;
    private Boolean withBackGround;
    private Boolean stretched;
}
