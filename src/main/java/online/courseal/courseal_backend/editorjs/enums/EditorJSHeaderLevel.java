package online.courseal.courseal_backend.editorjs.enums;


import com.fasterxml.jackson.annotation.JsonValue;

public enum EditorJSHeaderLevel {
    H1(1),
    H2(2),
    H3(3),
    H4(4),
    H5(5),
    H6(6);

    private final int level;

    EditorJSHeaderLevel(int level) {
        this.level = level;
    }

    @JsonValue
    public int getLevel() {
        return this.level;
    }
}
