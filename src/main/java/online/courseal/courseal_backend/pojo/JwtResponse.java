package online.courseal.courseal_backend.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private int id;
    private String username;
    private String userTag;
    private String email;

    public JwtResponse(String token, int id, String username, String userTag, String email){
        this.token = token;
        this.id = id;
        this.username = username;
        this.userTag = userTag;
        this.email = email;
    }
}
