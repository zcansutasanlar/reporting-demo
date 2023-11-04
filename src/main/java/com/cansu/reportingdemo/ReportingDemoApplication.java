package com.cansu.reportingdemo;

import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import javax.net.ssl.SSLContext;
import java.util.Collections;

@SpringBootApplication
public class ReportingDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReportingDemoApplication.class, args);
    }

    @Bean("restTemplate")
    public RestTemplate restTemplate() throws Exception {
       /* SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(null, (certificate, authType) -> true).build();
        */RestTemplate restTemplate;
       /* HttpComponentsClientHttpRequestFactory requestFactory
                = new HttpComponentsClientHttpRequestFactory();
*/
        restTemplate = new RestTemplate();
        return restTemplate;
    }
}
