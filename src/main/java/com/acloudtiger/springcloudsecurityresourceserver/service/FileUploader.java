package com.acloudtiger.springcloudsecurityresourceserver.service;

import com.acloudtiger.springcloudsecurityresourceserver.model.FileModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

public interface FileUploader {
    void uploadFile(MultipartFile file) throws IOException;

    Collection<FileModel> uploadedFileDetails();

}
