package com.flower.shop.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.flower.shop.Entity.imgEntity;
import com.flower.shop.Repository.imageRepository;

@Service
public class imageService {

@Autowired
private imageRepository imageRepository; 


    public String saveImage(String uplodeDir, MultipartFile file) throws IOException {
        String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path uploadPath = Path.of(uplodeDir);
        Path filePath = uploadPath.resolve(uniqueFileName);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
       
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    return uniqueFileName;
    }

    public byte[] getImages(String imageDirec, String imageName) throws IOException {
        Path imagePath = Path.of(imageDirec, imageName);
        if (Files.exists(imagePath)) {
            byte[] imageBytes = Files.readAllBytes(imagePath);
            return imageBytes;
        } else {
            return null;
        }
    }
    public String delteImages(String imageDirec, String imageName) throws IOException {
        Path imagePath = Path.of(imageDirec, imageName);
        if (Files.exists(imagePath)) {
             Files.delete(imagePath);
            return "deleted succuesful";
        } else {
            return "delete unsucceful";
        }
    }
}
