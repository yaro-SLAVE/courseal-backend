package online.courseal.courseal_backend.configs.editorjs.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import online.courseal.courseal_backend.configs.editorjs.enums.EditorJSHeaderLevel;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditorJSHeaderData {
    private String text;

    @Enumerated(EnumType.ORDINAL)
    private EditorJSHeaderLevel level;
}
