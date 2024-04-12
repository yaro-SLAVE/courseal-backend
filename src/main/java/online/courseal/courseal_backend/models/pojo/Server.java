package online.courseal.courseal_backend.models.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Server {
    private String server_name;
    private String server_description;
    private Boolean server_registration_enabled;
    private Boolean default_can_create_courses;
}
