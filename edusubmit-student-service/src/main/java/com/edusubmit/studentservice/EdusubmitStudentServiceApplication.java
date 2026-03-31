package com.edusubmit.studentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EdusubmitStudentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdusubmitStudentServiceApplication.class, args);
    }
}
