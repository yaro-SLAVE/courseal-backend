package online.courseal.courseal_backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import online.courseal.courseal_backend.coursedata.editorjs.*;
import online.courseal.courseal_backend.coursedata.editorjs.data.*;
import online.courseal.courseal_backend.coursedata.editorjs.enums.EditorJSHeaderLevel;
import online.courseal.courseal_backend.coursedata.editorjs.enums.EditorJSListStyle;
import online.courseal.courseal_backend.coursedata.editorjs.enums.EditorJSQuoteAlignment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class EditorJSTest {
    private final EditorJSContent sampleContent = new EditorJSContent(
        1711507166253L,
         Arrays.asList(new EditorJSBlock[] {
            new EditorJSHeader(
                "oi2FNWysnU",
                new EditorJSHeaderData(
                    "Sample Header h1",
                        EditorJSHeaderLevel.H1
                )
            ),
             new EditorJSHeader(
                 "c03rFjLjll",
                 new EditorJSHeaderData(
                     "Sample Header h6",
                     EditorJSHeaderLevel.H6
                 )
             ),
             new EditorJSParagraph(
                 "Y0FC-KXE9f",
                 new EditorJSParagraphData(
                     "Sample text<br>"
                 )
             ),
             new EditorJSParagraph(
                 "nNEesxxc6H",
                 new EditorJSParagraphData(
                    "<i>Sample italic text</i><br>"
                 )
             ),
             new EditorJSList(
                 "O6yNYMO2HR",
                 new EditorJSListData(
                     EditorJSListStyle.ORDERED,
                     Arrays.asList("ordered", "list")
                 )
             ),
             new EditorJSList(
                 "PodGxsipV_",
                 new EditorJSListData(
                     EditorJSListStyle.UNORDERED,
                     Arrays.asList("unordered","list")
                 )
             ),
             new EditorJSDelimiter(
                 "SSIiQM8QW_",
                 new Unit()
             ),
             new EditorJSQuote(
                 "3iWyZzWbmz",
                 new EditorJSQuoteData(
                     "A quote<br>",
                     "caption",
                     EditorJSQuoteAlignment.LEFT
                 )
             ),
             new EditorJSWarning(
                 "QK8ZIRhemb",
                 new EditorJSWarningData(
                     "WARNING",
                     "sample message"
                 )
             ),
             new EditorJSCode(
                 "h9bxessZnC",
                 new EditorJSCodeData(
                     "print(\"Hello World!)"
                 )
             )
        }),
        "2.29.1"
    );

    private final String sampleJson = "{\n" +
            "              \"time\": 1711507166253,\n" +
            "              \"blocks\": [\n" +
            "                {\n" +
            "                  \"id\": \"oi2FNWysnU\",\n" +
            "                  \"type\": \"header\",\n" +
            "                  \"data\": { \"text\": \"Sample Header h1\", \"level\": 1 }\n" +
            "                },\n" +
            "                {\n" +
            "                  \"id\": \"c03rFjLjll\",\n" +
            "                  \"type\": \"header\",\n" +
            "                  \"data\": { \"text\": \"Sample Header h6\", \"level\": 6 }\n" +
            "                },\n" +
            "                {\n" +
            "                  \"id\": \"Y0FC-KXE9f\",\n" +
            "                  \"type\": \"paragraph\",\n" +
            "                  \"data\": { \"text\": \"Sample text<br>\" }\n" +
            "                },\n" +
            "                {\n" +
            "                  \"id\": \"nNEesxxc6H\",\n" +
            "                  \"type\": \"paragraph\",\n" +
            "                  \"data\": { \"text\": \"<i>Sample italic text</i><br>\" }\n" +
            "                },\n" +
            "                {\n" +
            "                  \"id\": \"O6yNYMO2HR\",\n" +
            "                  \"type\": \"list\",\n" +
            "                  \"data\": { \"style\": \"ordered\", \"items\": [\"ordered\", \"list\"] }\n" +
            "                },\n" +
            "                {\n" +
            "                  \"id\": \"PodGxsipV_\",\n" +
            "                  \"type\": \"list\",\n" +
            "                  \"data\": { \"style\": \"unordered\", \"items\": [\"unordered\", \"list\"] }\n" +
            "                },\n" +
            "                { \"id\": \"SSIiQM8QW_\", \"type\": \"delimiter\", \"data\": {} },\n" +
            "                {\n" +
            "                  \"id\": \"3iWyZzWbmz\",\n" +
            "                  \"type\": \"quote\",\n" +
            "                  \"data\": {\n" +
            "                    \"text\": \"A quote<br>\",\n" +
            "                    \"caption\": \"caption\",\n" +
            "                    \"alignment\": \"left\"\n" +
            "                  }\n" +
            "                },\n" +
            "                {\n" +
            "                  \"id\": \"QK8ZIRhemb\",\n" +
            "                  \"type\": \"warning\",\n" +
            "                  \"data\": { \"title\": \"WARNING\", \"message\": \"sample message\" }\n" +
            "                },\n" +
            "                {\n" +
            "                  \"id\": \"h9bxessZnC\",\n" +
            "                  \"type\": \"code\",\n" +
            "                  \"data\": { \"code\": \"print(\\\"Hello World!)\" }\n" +
            "                }\n" +
            "              ],\n" +
            "              \"version\": \"2.29.1\"\n" +
            "            }";

    @Test
    void testDeserialization() throws JsonProcessingException {
        var mapper = new ObjectMapper();
        var deserialized = mapper.readValue(sampleJson, EditorJSContent.class);

        Assertions.assertEquals(sampleContent, deserialized);
    }

    @Test
    void testSerialization() throws JsonProcessingException {
        var mapper = new ObjectMapper();

        var serialized = mapper.writeValueAsString(sampleContent);

        var deserializedJson = mapper.readValue(sampleJson, EditorJSContent.class);
        var deserializedSerialized = mapper.readValue(serialized, EditorJSContent.class);

        Assertions.assertEquals(deserializedJson, deserializedSerialized);
    }

}
