package online.courseal.courseal_backend.editorjs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = EditorJSCode.class, name = "code"),
        @JsonSubTypes.Type(value = EditorJSDelimiter.class, name = "delimiter"),
        @JsonSubTypes.Type(value = EditorJSHeader.class, name = "header"),
        @JsonSubTypes.Type(value = EditorJSImage.class, name = "image"),
        @JsonSubTypes.Type(value = EditorJSLatex.class, name = "math"),
        @JsonSubTypes.Type(value = EditorJSList.class, name = "list"),
        @JsonSubTypes.Type(value = EditorJSParagraph.class, name = "paragraph"),
        @JsonSubTypes.Type(value = EditorJSQuote.class, name = "quote"),
        @JsonSubTypes.Type(value = EditorJSWarning.class, name = "warning")
})
public abstract class EditorJSBlock {
    protected String id;
}
