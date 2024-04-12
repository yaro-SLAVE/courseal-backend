package online.courseal.courseal_backend.models.pojo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Getter
@AllArgsConstructor
public class Server {
    private String serverName;
    private String serverDescription;
    private Boolean serverRegistrationEnabled;
    private Boolean defaultCanCreateCourses;

    public Server(){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File("static/courseal-config.json");
            Map<String, Object> serverMap = objectMapper.readValue(file, Map.class);

            this.serverName = (String) serverMap.get("server_name");
            this.serverDescription = (String) serverMap.get("server_description");
            this.serverRegistrationEnabled = (Boolean) serverMap.get("server_registration_enabled");
            this.defaultCanCreateCourses = (Boolean) serverMap.get("server_registration_enabled");
        } catch(IOException ignored){}
    }
}
