package by.javaguru.myproject.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

@Service
public class ImageService {

    @Value("${app.image.bucket}")
    private String bucket;

    @SneakyThrows
    public void upload(String imagePath, InputStream inputStream) {
        Path fullImagePath =Path.of(bucket,imagePath);

        try (inputStream){
            Files.createDirectories(fullImagePath.getParent());
            Files.write(fullImagePath,inputStream.readAllBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }

    @SneakyThrows
    public Optional<byte[]> get(String imagePath) {
        Path fullImagePath =Path.of(bucket,imagePath);
        return Files.exists(fullImagePath)
                ? Optional.of(Files.readAllBytes(fullImagePath))
                : Optional.empty();
    }
}
