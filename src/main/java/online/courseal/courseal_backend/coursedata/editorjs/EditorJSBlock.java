package online.courseal.courseal_backend.coursedata.editorjs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", include = JsonTypeInfo.As.EXTERNAL_PROPERTY)
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = EditorJSCode.class, name = "code"),
        @JsonSubTypes.Type(value = EditorJSDelimiter.class, name = "delimiter"),
        @JsonSubTypes.Type(value = EditorJSHeader.class, name = "header"),
        @JsonSubTypes.Type(value = EditorJSImage.class, name = "image"),
        @JsonSubTypes.Type(value = EditorJSLatex.class, name = "math"),
        @JsonSubTypes.Type(value = EditorJSList.class, name = "list"),
        @JsonSubTypes.Type(value = EditorJSParagraph.class, name = "paragraph"),
        @JsonSubTypes.Type(value = EditorJSQuote.class, name = "quote"),
        @JsonSubTypes.Type(value = EditorJSWarning.class, name = "warning"),
        @JsonSubTypes.Type(value = EditorJSEmbed.class, name = "embed")
})
public abstract sealed class EditorJSBlock
        permits EditorJSCode, EditorJSDelimiter, EditorJSHeader, EditorJSImage, EditorJSLatex, EditorJSList, EditorJSParagraph, EditorJSQuote, EditorJSWarning, EditorJSEmbed { }
