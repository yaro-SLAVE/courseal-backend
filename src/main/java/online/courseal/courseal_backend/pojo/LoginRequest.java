package online.courseal.courseal_backend.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String userTag;
    private String password;
}
