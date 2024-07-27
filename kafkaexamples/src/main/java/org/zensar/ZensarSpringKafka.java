package org.zensar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootConfiguration
@SpringBootApplication
public class ZensarSpringKafka {
    public static void main(String[] args) {
        SpringApplication.run(ZensarSpringKafka.class, args);
        /*SpringApplication app =new SpringApplication(ZensarSpringKafka.class);
        app.setWebApplicationType(WebApplicationType.REACTIVE);
        app.run(args);*/
    }
}