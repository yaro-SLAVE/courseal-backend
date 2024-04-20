package online.courseal.courseal_backend.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UsersListResponse {
    @JsonProperty("usertag")
    private String userTag;
    @JsonProperty("username")
    private  String userName;
    @JsonProperty("xp")
    private Integer xp;
}
