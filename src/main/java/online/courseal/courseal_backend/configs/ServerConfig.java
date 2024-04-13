package online.courseal.courseal_backend.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import online.courseal.courseal_backend.models.pojo.Server;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class ServerConfig {
    private Server server;

    public ServerConfig(){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File("static/courseal-config.json");
            server = objectMapper.readValue(file, Server.class);
        } catch (IOException ignored){}
    }

    public Server getServerInfo(){
        return server;
    }
}
