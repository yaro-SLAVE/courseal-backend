package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.records.ServerInfo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courseal-info")
public class ServerInfoController {
    private final String serverName = "Courseal";
    private final String description = "The coolest education platform!!!";
    private final boolean canRegistering = true;

    @PostMapping
    public ServerInfo serverInfo(){
        return new ServerInfo(serverName, description, canRegistering);
    }
}
