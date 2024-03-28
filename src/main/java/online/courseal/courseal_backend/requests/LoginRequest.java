package online.courseal.courseal_backend.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String usertag;
    private String password;
}
