package online.courseal.courseal_backend.services;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageService {

    public Optional<String> saveImage(MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType != null && (contentType.equals("image/png") || contentType.equals("image/jpeg") ||
                contentType.equals("image/webp") || contentType.equals("image/gif"))) {
            String uuid = UUID.randomUUID().toString();

            try {
                Files.createDirectories(Paths.get("data", "images"));

                BufferedImage image = ImageIO.read(file.getInputStream());
                ImageIO.write(image, "png", new File("data/images/" + uuid));
                return Optional.of(uuid);
            } catch (IOException e) {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    public ByteArrayResource getImage(String uuid) {
        var path = Paths.get("data", "images", uuid);
        try {
            return new ByteArrayResource(Files.readAllBytes(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
