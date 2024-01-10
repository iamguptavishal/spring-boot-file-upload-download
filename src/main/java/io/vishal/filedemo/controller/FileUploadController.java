package io.vishal.filedemo.controller;

import io.vishal.filedemo.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/uploadFile")
    public void uploadFile(@RequestParam("file")MultipartFile file) {
        storageService.storeFile(file);
    }
}
