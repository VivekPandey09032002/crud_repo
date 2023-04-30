package com.learn.testing.utility;

import com.learn.testing.exception.CustomBookException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtility {

    public static final String UPLOAD_DIR;

    static {
        try {
            UPLOAD_DIR = new ClassPathResource("static/file/").getFile().getAbsolutePath();
        } catch (IOException e) {
            throw new CustomBookException("no static resource found", HttpStatus.NOT_FOUND);
        }
    }

    private FileUploadUtility() {
    }

    public static boolean uploadFile(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
