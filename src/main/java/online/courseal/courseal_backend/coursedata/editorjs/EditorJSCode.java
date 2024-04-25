package online.courseal.courseal_backend.coursedata.editorjs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import online.courseal.courseal_backend.coursedata.editorjs.data.EditorJSCodeData;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class EditorJSCode extends EditorJSBlock{
    private String id;
    private EditorJSCodeData data;
}
