package online.courseal.courseal_backend.controllers;

import online.courseal.courseal_backend.configs.ServerConfig;
import online.courseal.courseal_backend.coursedata.editorjs.data.EditorJSImageFile;
import online.courseal.courseal_backend.responses.records.EditorJSImageResponse;
import online.courseal.courseal_backend.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/static")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ImageController {
    @Autowired
    ImageService imageService;

    @Autowired
    ServerConfig serverConfig;

    @PostMapping("editorjsimage")
    public EditorJSImageResponse uploadEditorJSImage(@RequestParam("image") MultipartFile file) {
        String host = serverConfig.getServerInfo().getServerDomain();

        return imageService.saveImage(file)
            .map(uuid -> new EditorJSImageResponse(
                1,
                new EditorJSImageFile(
                    host + "/api/static/image/" + uuid
                )
            ))
            .orElse(new EditorJSImageResponse(
                0,
                null
            ));
    }

    @GetMapping(value = "image/{uuid}", produces = MediaType.IMAGE_PNG_VALUE)
    public Resource getImage(@PathVariable String uuid) {
        return imageService.getImage(uuid);
    }

}
