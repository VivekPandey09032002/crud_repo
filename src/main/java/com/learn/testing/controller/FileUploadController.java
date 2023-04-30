package com.learn.testing.controller;

import com.learn.testing.utility.FileUploadUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileUploadController {

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file){
        if(file.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no file found");
        }else if(FileUploadUtility.uploadFile(file)){
            return ResponseEntity.status(HttpStatus.CREATED).body("file upload done");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("cannot upload file");
    }
}
