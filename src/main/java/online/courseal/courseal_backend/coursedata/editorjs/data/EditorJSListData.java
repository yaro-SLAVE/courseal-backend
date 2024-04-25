package online.courseal.courseal_backend.coursedata.editorjs.data;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import online.courseal.courseal_backend.coursedata.editorjs.enums.EditorJSListStyle;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditorJSListData {
    @Enumerated(EnumType.STRING)
    private EditorJSListStyle style;
    private List<String> items;
}
