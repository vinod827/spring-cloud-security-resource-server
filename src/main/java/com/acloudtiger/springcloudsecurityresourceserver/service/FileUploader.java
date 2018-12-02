package com.acloudtiger.springcloudsecurityresourceserver.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploader {
    void uploadFile(MultipartFile file) throws IOException;

}
