package online.courseal.courseal_backend.coursedata.editorjs.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import online.courseal.courseal_backend.coursedata.editorjs.enums.EditorJSEmbedService;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditorJSEmbedData {
    private EditorJSEmbedService service;
    private String source;
    private String embed;
    private Integer width;
    private Integer height;
    private String caption;
}
