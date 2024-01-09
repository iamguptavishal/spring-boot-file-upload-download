package io.vishal.filedemo.service;

import io.vishal.filedemo.exception.StorageException;
import io.vishal.filedemo.storage.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemStorageService implements StorageService{

    private final Path fileStorageLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties storageProperties) {
        this.fileStorageLocation = Path.of(storageProperties.getUploadDir()).toAbsolutePath().normalize();
    }

    @Override
    public void storeFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file.");
        }

    }

    @Override
    public Resource loadFileAsResource() {
        return null;
    }
}
