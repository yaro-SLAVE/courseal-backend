package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.configs.ServerConfig;
import online.courseal.courseal_backend.models.pojo.Server;
import online.courseal.courseal_backend.responses.records.ServerInfo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courseal-info")
public class ServerInfoController {
    @GetMapping
    public ServerInfo serverInfo(){
        Server server = ServerConfig.getServerInfo();
        if (server != null) {
            return new ServerInfo(
                    server.getServer_name(),
                    server.getServer_description(),
                    server.getServer_registration_enabled()
            );
        } else {
            return new ServerInfo(
                    "fuck",
                    "fuck",
                    false
            );
        }
    }
}
