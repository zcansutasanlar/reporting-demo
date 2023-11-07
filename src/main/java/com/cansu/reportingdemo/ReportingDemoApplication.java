package com.cansu.reportingdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource("application.properties")
public class ReportingDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReportingDemoApplication.class, args);
    }

}
