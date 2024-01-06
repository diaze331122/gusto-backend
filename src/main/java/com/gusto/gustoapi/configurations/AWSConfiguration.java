package com.gusto.gustoapi.configurations;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.S3ClientOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.util.Optional;

@Configuration
public class AWSConfiguration {

    public AWSCredentials credentials() {
        return new BasicAWSCredentials(
                System.getenv("aws.s3.access.key.id"),
                System.getenv("aws.s3.secret.access.key")
        );
    }

    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials()))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(System.getenv("aws.s3.endpoint"), "us-east-2"))
                .enablePathStyleAccess()
                .build();
    }
}
