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

        /*String clientRegion = "*** Client region ***";
        String bucketName = "acloudtiger-bucket";
        String stringObjKeyName = "*** String object key name ***";
        String fileObjKeyName = "*** File object key name ***";
        String fileName = "*** Path to file to upload ***";

        try {
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(clientRegion)
                    .withCredentials(new ProfileCredentialsProvider())
                    .build();

            // Upload a text string as a new object.
            s3Client.putObject(bucketName, stringObjKeyName, "Uploaded String Object");

            // Upload a file as a new object with ContentType and title specified.
            PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, new File(fileName));
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("plain/text");
            metadata.addUserMetadata("x-amz-meta-title", "someTitle");
            request.setMetadata(metadata);
            s3Client.putObject(request);
        }
        catch(AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            e.printStackTrace();
        }
        catch(SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
        }*/

        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAI6SK6NOQ6CPUB3CQ",
                "bJbLfow/Mj87lGaS8/GTZ9Kw+e46QwfqozS4UQfn"
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
            //return;
        }else{
            s3client.createBucket(bucketName);
        }

        List<Bucket> buckets = s3client.listBuckets();
        for(Bucket bucket : buckets) {
            System.out.println(bucket.getName());
        }


/*        File concertFile = new File("C:\\vinod\\projects\\my-hacks\\file\\"+file.getOriginalFilename());
        concertFile.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(concertFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();*/

        s3client.putObject(
                bucketName,
                "Document/"+file.getOriginalFilename(),
                new File(String.valueOf(file.getBytes()))
        );

        ObjectListing objectListing = s3client.listObjects(bucketName);
        for(S3ObjectSummary os : objectListing.getObjectSummaries()) {
            logger.info(os.getKey());
        }
    }
}
