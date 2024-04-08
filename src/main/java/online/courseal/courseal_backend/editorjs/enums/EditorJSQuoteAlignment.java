package online.courseal.courseal_backend.editorjs.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum EditorJSQuoteAlignment {
    @JsonProperty("left") LEFT,
    @JsonProperty("right") RIGHT
}
