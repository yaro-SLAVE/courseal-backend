package online.courseal.courseal_backend.models.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Server {
    @JsonProperty("server_name")
    private String serverName;
    @JsonProperty("server_description")
    private String serverDescription;
    @JsonProperty("server_registration_enabled")
    private Boolean serverRegistrationEnabled;
    @JsonProperty("default_can_create_courses")
    private Boolean defaultCanCreateCourses;
    @JsonProperty("server_domain")
    private String serverDomain;
}
