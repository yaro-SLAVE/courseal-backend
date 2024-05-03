package online.courseal.courseal_backend.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class RegisterRequest {
    @JsonProperty("usertag")
    private String usertag;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;


}
