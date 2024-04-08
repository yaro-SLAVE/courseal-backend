package online.courseal.courseal_backend.configs.editorjs.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EditorJSHeaderLevel {
    @JsonProperty(index = 1) H1,
    @JsonProperty(index = 2) H2,
    @JsonProperty(index = 3)H3,
    @JsonProperty(index = 4)H4,
    @JsonProperty(index = 5)H5,
    @JsonProperty(index = 6)H6
}
