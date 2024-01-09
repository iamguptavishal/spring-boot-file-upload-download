package io.vishal.filedemo.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    void storeFile(MultipartFile file);
    Resource loadFileAsResource();
}
