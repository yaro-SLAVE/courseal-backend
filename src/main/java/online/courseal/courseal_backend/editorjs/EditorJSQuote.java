package online.courseal.courseal_backend.editorjs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import online.courseal.courseal_backend.editorjs.data.EditorJSQuoteData;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditorJSQuote extends EditorJSBlock{
    private EditorJSQuoteData data;
}