package com.acloudtiger.springcloudsecurityresourceserver.controller;

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

    @PostMapping(name = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(@RequestParam("file")MultipartFile file) throws IOException {
        File concertFile = new File("C:\\vinod\\projects\\my-hacks\\file\\"+file.getOriginalFilename());
        concertFile.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(concertFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        return new ResponseEntity<>("This file has been uploaded successfully", HttpStatus.OK);
    }

}