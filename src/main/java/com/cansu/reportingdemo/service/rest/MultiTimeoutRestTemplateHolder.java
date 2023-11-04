package com.cansu.reportingdemo.service.rest;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MultiTimeoutRestTemplateHolder {


    @Getter
    private Integer connMaxTotal = 160;

    @Getter
    private Integer connMaxPerRoute=80;

    @Autowired
    private TimeoutConfigurations timeoutConfigurations;

    private Map<TimeoutLevel, RestTemplate> restTemplates;
    @PostConstruct
    public void initializeRestTemplates(){
        restTemplates = new HashMap<>();
        restTemplates.put(TimeoutLevel.QUICK, buildTimeoutRestTemplate(TimeoutLevel.QUICK));
        restTemplates.put(TimeoutLevel.INTERMEDIATE, buildTimeoutRestTemplate(TimeoutLevel.INTERMEDIATE));
        restTemplates.put(TimeoutLevel.MEDIUM, buildTimeoutRestTemplate(TimeoutLevel.MEDIUM));
        restTemplates.put(TimeoutLevel.SLOW, buildTimeoutRestTemplate(TimeoutLevel.SLOW));
        restTemplates.put(TimeoutLevel.SLOTH, buildTimeoutRestTemplate(TimeoutLevel.SLOTH));
    }
    private RestTemplate buildTimeoutRestTemplate(TimeoutLevel timeoutLevel) {

        CloseableHttpClient httpClient = HttpClients
                    .custom()
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                    .setSSLContext(buildAcceptingSslContext())
                    .build();


        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpComponentsClientHttpRequestFactory.setHttpClient(httpClient);

        //set timeouts
        TimeoutConfigurations.TimeoutConfig timeoutConfig = timeoutConfigurations.getTimeoutConfigForTimeoutLevel(timeoutLevel);
        httpComponentsClientHttpRequestFactory.setConnectTimeout(timeoutConfig.getConnectTimeout());
        httpComponentsClientHttpRequestFactory.setReadTimeout(timeoutConfig.getReadTimeout());
        httpComponentsClientHttpRequestFactory.setConnectionRequestTimeout(timeoutConfig.getConnectionRequestTimeout());

        BufferingClientHttpRequestFactory bufferingClientHttpRequestFactory = new BufferingClientHttpRequestFactory(httpComponentsClientHttpRequestFactory);

        RestTemplate restTemplate = new RestTemplate(bufferingClientHttpRequestFactory);
        List<ClientHttpRequestInterceptor> interceptorList = new ArrayList<>();
        interceptorList.add((request, body, execution) -> {
            try{
                ClientHttpResponse response  = execution.execute(request, body);
                return response;
            }catch (Exception ex){
                ex.printStackTrace();
                throw ex;
            }
        });

        restTemplate.setInterceptors(interceptorList );
        return restTemplate;
    }
    public RestTemplate getRestTemplateForLevel(TimeoutLevel timeoutLevel) {
        return restTemplates.get(timeoutLevel);
    }
    @SneakyThrows
    private SSLContext buildAcceptingSslContext() {
        return SSLContexts.custom()
                .loadTrustMaterial(null, (chain, authType) -> true)
                .build();
    }
}
