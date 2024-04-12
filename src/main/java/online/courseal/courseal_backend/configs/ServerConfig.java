package online.courseal.courseal_backend.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import online.courseal.courseal_backend.models.pojo.Server;

import java.io.File;
import java.io.IOException;

public class ServerConfig {
    public static Server getServerInfo(){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File("static/courseal-config.json.example");
            Server server = objectMapper.readValue(file, Server.class);
            return server;
        } catch(IOException ignored){return null;}
    }
}
