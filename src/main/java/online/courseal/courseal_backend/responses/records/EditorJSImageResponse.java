package online.courseal.courseal_backend.responses.records;

import online.courseal.courseal_backend.coursedata.editorjs.data.EditorJSImageFile;

public record EditorJSImageResponse(
    Integer success,
    EditorJSImageFile file
) {}
