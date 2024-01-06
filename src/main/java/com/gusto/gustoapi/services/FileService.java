package com.gusto.gustoapi.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

//TODO: Create file Object put, delete functions.

@Service
public class FileService {

    @Autowired
    AmazonS3 amazonS3Client;

    public void putObject(String bucket, String key, InputStream inputStream, ObjectMetadata metadata) throws IOException {
        amazonS3Client.putObject(bucket, key, inputStream, metadata);
    }

    public void deleteObject(String bucket, String key) throws IOException {
        amazonS3Client.deleteObject(bucket, key);
    }

}
