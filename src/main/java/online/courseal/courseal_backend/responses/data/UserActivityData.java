package online.courseal.courseal_backend.responses.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserActivityData {
    @JsonProperty("day")
    private LocalDate day;
    @JsonProperty("xp")
    private Integer xp;
}
