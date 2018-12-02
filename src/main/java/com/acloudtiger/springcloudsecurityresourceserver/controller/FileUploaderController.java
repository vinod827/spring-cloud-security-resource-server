package com.acloudtiger.springcloudsecurityresourceserver.controller;

import com.acloudtiger.springcloudsecurityresourceserver.service.FileUploader;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@Api(value="/upload",description="File upload",produces ="application/json")
public class FileUploaderController {

    private FileUploader fileUploader;

    @Autowired
    public FileUploaderController(FileUploader fileUploader) {
        this.fileUploader = fileUploader;
    }

    @ApiOperation(value="upload file to cloud",response=Object.class)
    @ApiResponses(value={
            @ApiResponse(code=200,message="Uploaded file to cloud",response=Object.class),
            @ApiResponse(code=500,message="Internal Server Error"),
            @ApiResponse(code=404,message="File not uploaded")
    })

/*    @PostMapping(name = "/directory", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFileToDirectory(@RequestParam("file") MultipartFile file) throws IOException {
        File concertFile = new File("C:\\vinod\\projects\\my-hacks\\file\\"+file.getOriginalFilename());
        concertFile.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(concertFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        return new ResponseEntity<>("This file has been uploaded successfully", HttpStatus.OK);
    }*/

    @PostMapping(name = "/upload/cloud", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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