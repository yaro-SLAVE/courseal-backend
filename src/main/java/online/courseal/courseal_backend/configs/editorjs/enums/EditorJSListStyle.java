package online.courseal.courseal_backend.configs.editorjs.enums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum EditorJSListStyle {
    @JsonProperty("ordered") ORDERED,
    @JsonProperty("unordered") UNORDERED
}
