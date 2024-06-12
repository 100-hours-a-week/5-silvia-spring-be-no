package com.avoworld.springbe.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadService {

    private final String uploadDir = "./uploads/";

    public String storeFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("File is empty");
        }
        byte[] bytes = file.getBytes();
        Path path = Paths.get(uploadDir + file.getOriginalFilename());
        Files.write(path, bytes);
        return path.toString();
    }
}