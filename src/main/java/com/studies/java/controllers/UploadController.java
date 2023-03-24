package com.studies.java.controllers;


import com.studies.java.services.SaveFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private SaveFileService saveFileService;

    @PostMapping
    public HttpStatus uploadFile(@RequestParam MultipartFile file){
        saveFileService.saveFile(file);

        return HttpStatus.valueOf(200);
    }
}
