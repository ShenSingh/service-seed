package com.edusubmit.submissionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EdusubmitSubmissionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdusubmitSubmissionServiceApplication.class, args);
    }
}
