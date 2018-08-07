package com.trendyol.usereventregistration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableDiscoveryClient
public class UserEventRegistrationApplication {

   @Autowired
   private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(UserEventRegistrationApplication.class, args);
    }

}
