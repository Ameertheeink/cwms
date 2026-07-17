package com.corporatewebsite.common.file.service;

import com.corporatewebsite.common.exception.BadRequestException;
import com.corporatewebsite.common.file.dto.FileUploadResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDirectory;

    @Override
    public FileUploadResponse storeFile(MultipartFile file, String folder)
            throws IOException {

        // 1. Validate file
        if (file == null || file.isEmpty()) {
            throw new BadRequestException("Uploaded file is empty.");
        }

        // 2. Original filename
        String originalFileName = file.getOriginalFilename();

        // 3. Extract extension
        String extension = "";

        if (originalFileName != null &&
                originalFileName.contains(".")) {

            extension = originalFileName.substring(
                    originalFileName.lastIndexOf("."));
        }

        // 4. Generate UUID filename
        String storedFileName =
                UUID.randomUUID() + extension;

        // 5. Create upload directory
        Path uploadPath = Paths.get(uploadDirectory, folder);

        Files.createDirectories(uploadPath);

        // 6. Final file path
        Path targetLocation =
                uploadPath.resolve(storedFileName);

        // 7. Copy file
        Files.copy(
                file.getInputStream(),
                targetLocation,
                StandardCopyOption.REPLACE_EXISTING
        );

        // 8. Return response
        return new FileUploadResponse(
                originalFileName,
                storedFileName,
                targetLocation.toString(),
                file.getSize(),
                file.getContentType()
        );
    }

    @Override
    public void deleteFile(String filePath)
            throws IOException {

        Path path = Paths.get(filePath);

        Files.deleteIfExists(path);
    }
}