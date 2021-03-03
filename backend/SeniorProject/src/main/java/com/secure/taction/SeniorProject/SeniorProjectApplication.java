package com.secure.taction.SeniorProject;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3Client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeniorProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeniorProjectApplication.class, args);
		
		InstanceProfileCredentialsProvider credentials =
			InstanceProfileCredentialsProvider.createAsyncRefreshingProvider(true);
		
		AmazonS3Client.builder()
			.withCredentials(credentials)
			.build();

	}

}
