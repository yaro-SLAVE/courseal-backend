package online.courseal.courseal_backend.configs.editorjs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import online.courseal.courseal_backend.configs.editorjs.data.EditorJSListData;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditorJSList extends EditorJSBlock{
    private EditorJSListData data;
}
