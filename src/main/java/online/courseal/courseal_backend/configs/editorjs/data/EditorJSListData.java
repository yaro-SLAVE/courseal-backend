package online.courseal.courseal_backend.configs.editorjs.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import online.courseal.courseal_backend.configs.editorjs.enums.EditorJSListStyle;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditorJSListData {
    @Enumerated(EnumType.STRING)
    private EditorJSListStyle style;
    private ArrayList<String> items;
}
