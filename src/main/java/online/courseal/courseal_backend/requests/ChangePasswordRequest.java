package online.courseal.courseal_backend.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRequest {
    private String old_password;
    private String new_password;
}
