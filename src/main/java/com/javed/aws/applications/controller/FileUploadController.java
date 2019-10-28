package com.javed.aws.applications.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.Random;

@Controller
public class FileUploadController {

    private static final String UPLOAD_DIRECTORY ="/resources/images/";

    @RequestMapping(value = "uploadForm", method = RequestMethod.GET)
    public ModelAndView uploadForm(){
        return new ModelAndView("uploadForm");
    }

    @RequestMapping(value = "saveFile", method = RequestMethod.POST)
    public ModelAndView saveImage(@RequestParam CommonsMultipartFile file, HttpSession httpSession) throws IOException {

        String bucketName = "user-service-bucket-javed547";
        ServletContext context = httpSession.getServletContext();
        String path = context.getRealPath(UPLOAD_DIRECTORY);
        String filename = file.getOriginalFilename();

        System.out.println(path+" "+filename);

        byte[] bytes = file.getBytes();
        BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(
                new File(path + File.separator + filename)));
        stream.write(bytes);
        stream.flush();
        stream.close();

        try {
            Region region = Region.AP_SOUTH_1;
            S3Client s3Client = S3Client
                    .builder()
                    .region(region)
                    .build();

            PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucketName).key(filename).build();
            RequestBody requestBody = RequestBody.fromByteBuffer(ByteBuffer.wrap(bytes));

            s3Client.putObject(putObjectRequest, requestBody);

        } catch (AwsServiceException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (SdkClientException e) {
            System.err.println(e.getMessage());
            System.exit(2);
        }

        return new ModelAndView("uploadForm","filesuccess","File successfully saved!");
    }

}
