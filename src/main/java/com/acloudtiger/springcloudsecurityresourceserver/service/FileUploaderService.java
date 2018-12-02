package com.acloudtiger.springcloudsecurityresourceserver.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service("fileUploader")
public class FileUploaderService implements FileUploader {

    private static Logger logger = LoggerFactory.getLogger(FileUploaderService.class);


    @Override
    public void uploadFile(MultipartFile file) throws IOException {
        logger.info("Entering uploadFile@FileUploaderService");

        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAIKQAS6ZLQRLVAB6A",
                "lfnCWnZ10hvvnkrEwkEpoa3u+qIKukmhNwFszvKI"
        );

        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_2)
                .build();

        String bucketName = "acloudtiger-bucket";
        if(s3client.doesBucketExist(bucketName)) {
            logger.info("Bucket name is not available."
                    + " Try again with a different Bucket name.");
        }else{
            s3client.createBucket(bucketName);
        }

        List<Bucket> buckets = s3client.listBuckets();
        for(Bucket bucket : buckets) {
            System.out.println(bucket.getName());
        }

        File newFile = multipartToFile(file);

        s3client.putObject(
                bucketName,
                file.getOriginalFilename(),
                newFile);

        ObjectListing objectListing = s3client.listObjects(bucketName);
        for(S3ObjectSummary os : objectListing.getObjectSummaries()) {
            logger.info(os.getKey());
        }
        logger.info("Exiting uploadFile@FileUploaderService");
    }

    /**
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    private File multipartToFile(MultipartFile multipartFile) throws IOException {
        logger.info("Entering multipartToFile@FileUploaderService");
        File convFile = new File(multipartFile.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        logger.info("Exiting multipartToFile@FileUploaderService");
        return convFile;
    }
}