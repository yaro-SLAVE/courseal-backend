package online.courseal.courseal_backend.configs.editorjs.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import online.courseal.courseal_backend.configs.editorjs.enums.EditorJSQuoteAlignment;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditorJSQuoteData {
    private String text;
    private String caption;
    private EditorJSQuoteAlignment alignment;
}
