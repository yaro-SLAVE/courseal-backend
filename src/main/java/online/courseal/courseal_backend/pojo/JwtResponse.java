package online.courseal.courseal_backend.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Integer id;
    private String userName;
    private String userTag;
    private String email;

    public JwtResponse(String token, Integer id, String username, String userTag, String email){
        this.token = token;
        this.id = id;
        this.userName = username;
        this.userTag = userTag;
        this.email = email;
    }
}
