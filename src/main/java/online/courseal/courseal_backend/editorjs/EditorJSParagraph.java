package online.courseal.courseal_backend.editorjs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import online.courseal.courseal_backend.editorjs.data.EditorJSParagraphData;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class EditorJSParagraph extends EditorJSBlock {
    private String id;
    private EditorJSParagraphData data;
}
