package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.models.pojo.Server;
import online.courseal.courseal_backend.responses.records.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courseal-info")
public class ServerInfoController {
    @Autowired
    Server server;

    @GetMapping
    public ServerInfo serverInfo(){
        return new ServerInfo(
                server.getServerName(),
                server.getServerDescription(),
                server.getServerRegistrationEnabled()
        );
    }
}
