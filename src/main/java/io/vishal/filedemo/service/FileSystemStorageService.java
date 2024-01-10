package io.vishal.filedemo.service;

import io.vishal.filedemo.exception.FileStorageException;
import io.vishal.filedemo.exception.StorageException;
import io.vishal.filedemo.storage.StorageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileSystemStorageService implements StorageService{
    static final Logger log = LoggerFactory.getLogger(FileSystemStorageService.class);

    private final Path fileStorageLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties storageProperties) {
        this.fileStorageLocation = Path.of(storageProperties.getUploadDir()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not initialize storage directory");
        }
        log.info("Target Directory created");
    }

    @Override
    public void storeFile(MultipartFile file) {
        log.info("storeFile started...");
        try {
            if (Objects.isNull(file) || file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            Path destinationFile = fileStorageLocation.resolve(file.getOriginalFilename()).toAbsolutePath();
            /*Security check*/
            if(!destinationFile.getParent().equals(fileStorageLocation) || destinationFile.toString().contains("..")) {
                throw new StorageException("Cannot store file outside specified directory. Filename contains invalid path sequence");
            }
            // Copy file to the target location (Replacing existing file with the same name)
            try (InputStream in = file.getInputStream()) {
                Files.copy(in, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new FileStorageException("Unable to store file = "+file.getOriginalFilename() + "Please try again.");
        }
        log.info("storeFile completed...");
    }

    @Override
    public Resource loadFileAsResource() {
        return null;
    }
}
