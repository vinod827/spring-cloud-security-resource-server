package com.acloudtiger.springcloudsecurityresourceserver.controller;

import com.acloudtiger.springcloudsecurityresourceserver.service.FileUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class FileUploaderController {

    private FileUploader fileUploader;

    @Autowired
    public FileUploaderController(FileUploader fileUploader) {
        this.fileUploader = fileUploader;
    }

/*    @PostMapping(name = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFileToDirectory(@RequestParam("file") MultipartFile file) throws IOException {
        File concertFile = new File("C:\\vinod\\projects\\my-hacks\\file\\"+file.getOriginalFilename());
        concertFile.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(concertFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        return new ResponseEntity<>("This file has been uploaded successfully", HttpStatus.OK);
    }*/

    @PostMapping(name = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFileToS3(@RequestParam("file") MultipartFile file) {
        try {
            fileUploader.uploadFile(file);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Exception thrown", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("The file has been uploaded successfully", HttpStatus.OK);
    }

}