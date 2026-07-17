package com.corporatewebsite.common.file.service;

import com.corporatewebsite.common.file.dto.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {

    /**
     * Stores a single file inside the given folder.
     *
     * Example:
     * uploads/events/
     * uploads/services/
     */
    FileUploadResponse storeFile(MultipartFile file, String folder)
            throws IOException;

    /**
     * Deletes a stored file.
     */
    void deleteFile(String filePath) throws IOException;

}