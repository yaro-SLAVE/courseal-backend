package online.courseal.courseal_backend.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    private String userTag;
    private String username;
    private String password;
}
