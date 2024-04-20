package online.courseal.courseal_backend.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import online.courseal.courseal_backend.responses.data.UserActivityData;

import java.util.List;

@AllArgsConstructor
public class UserActivityResponse {
    @JsonProperty("usertag")
    private String userTag;
    @JsonProperty("activity")
    private List<UserActivityData> userActivityDataList;
}
