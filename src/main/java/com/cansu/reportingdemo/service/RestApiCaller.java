package com.cansu.reportingdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Service
public class RestApiCaller {

    @Autowired
    private RestTemplate restTemplate;

    public <T> T postRequest(String uri, Class<T> type, Object reqInfo, HttpHeaders headers) throws Exception {
        if (headers.getContentType() == null){
            headers.setContentType(MediaType.APPLICATION_JSON);
        }
        HttpEntity request = new HttpEntity(reqInfo, headers);
        Optional<ResponseEntity<T>> response = Optional.empty();
        try {
            response =  Optional.of(this.restTemplate.exchange(uri, HttpMethod.POST, request, type));

        }catch (HttpClientErrorException ex){
            log.error("RestApiCaller.HttpClientErrorException : " + ex.getMessage());
            throw ex;
        }catch (Exception ex){
            log.error("RestApiCaller.HttpClientErrorException : " + ex.getMessage());
            throw ex;
        }
        if (response.get().getStatusCode() == HttpStatus.OK || response.get().getStatusCode() == HttpStatus.CREATED || response.get().getStatusCode() == HttpStatus.NOT_MODIFIED) {
            return response.get().getBody();
        }
        throw new Exception(response.get().getStatusCode().toString());
    }
}
