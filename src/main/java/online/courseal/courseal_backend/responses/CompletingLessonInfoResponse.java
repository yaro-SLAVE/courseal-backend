package online.courseal.courseal_backend.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import online.courseal.courseal_backend.responses.data.LessonMistakesData;

import java.util.List;

@AllArgsConstructor
public class CompletingLessonInfoResponse {
    @JsonProperty("xp")
    private Integer xp;
    @JsonProperty("completed")
    private boolean completed;
    @JsonProperty("mistakes")
    private List<LessonMistakesData> mistakes;
}
