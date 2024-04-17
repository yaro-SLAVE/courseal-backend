package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.configs.ServerConfig;
import online.courseal.courseal_backend.models.pojo.Server;
import online.courseal.courseal_backend.responses.records.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courseal-info")
public class ServerInfoController {
    @Autowired
    ServerConfig serverConfig;

    @GetMapping
    public ServerInfo serverInfo(){
        Server server = serverConfig.getServerInfo();
        return new ServerInfo(
                server.getServerName(),
                server.getServerDescription(),
                server.getServerRegistrationEnabled()
        );
    }
}
