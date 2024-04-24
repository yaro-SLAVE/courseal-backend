package online.courseal.courseal_backend.coursedata.editorjs.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import online.courseal.courseal_backend.coursedata.editorjs.enums.EditorJSHeaderLevel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditorJSHeaderData {
    private String text;
    private EditorJSHeaderLevel level;
}
