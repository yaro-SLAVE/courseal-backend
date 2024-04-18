package online.courseal.courseal_backend.editorjs.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import online.courseal.courseal_backend.editorjs.enums.EditorJSQuoteAlignment;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditorJSQuoteData {
    private String text;
    private String caption;
    private EditorJSQuoteAlignment alignment;
}
