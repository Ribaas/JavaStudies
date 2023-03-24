package com.studies.java.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class SaveFileService {

    @Value("${user.directory.root}")
    private String userDirectoryRoot;

    @Value("${user.directory.sheets}")
    private String userDirectorySheets;

    public void saveFile(MultipartFile file){
        Path directoryPath = Paths.get(userDirectoryRoot, userDirectorySheets);
        Path filePath = directoryPath.resolve(file.getOriginalFilename());

        try{
            Files.createDirectories(directoryPath);
            file.transferTo(filePath.toFile());
        } catch(IOException e) {
            throw new RuntimeException("Problemas na tentativa de salvar arquivo");
        }
    }
}
