package online.courseal.courseal_backend.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Integer id;
    private String userTag;

    public JwtResponse(String token, Integer id,  String userTag){
        this.token = token;
        this.id = id;
        this.userTag = userTag;
    }
}
