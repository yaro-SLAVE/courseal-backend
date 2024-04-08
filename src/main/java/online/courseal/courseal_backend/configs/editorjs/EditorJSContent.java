package online.courseal.courseal_backend.configs.editorjs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditorJSContent {
    private Long time;
    private ArrayList<EditorJSBlock> blocks;
    private String version;
}
