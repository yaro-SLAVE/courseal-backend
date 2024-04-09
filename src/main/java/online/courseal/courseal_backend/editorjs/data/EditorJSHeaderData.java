package online.courseal.courseal_backend.editorjs.data;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import online.courseal.courseal_backend.editorjs.enums.EditorJSHeaderLevel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditorJSHeaderData {
    private String text;
    private EditorJSHeaderLevel level;
}
