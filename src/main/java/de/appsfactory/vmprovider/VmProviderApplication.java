package de.appsfactory.vmprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class VmProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(VmProviderApplication.class, args);
    }
}