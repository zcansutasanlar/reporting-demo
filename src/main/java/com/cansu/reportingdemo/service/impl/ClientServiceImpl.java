package com.cansu.reportingdemo.service.impl;

import com.cansu.reportingdemo.model.request.GetTransactionRequest;
import com.cansu.reportingdemo.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final RestTemplate restApiCaller;

    @Value("${report.workingDirectory.url:https://sandbox-reporting.rpdpymnt.com}")
    String workingDirectory;

    @Value("${report.client.get.url}")
    String getUrl;

    @Override
    public Object getClient(String authToken, GetTransactionRequest request) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", authToken);
            HttpEntity requestEntity = new HttpEntity(request, headers);
            ResponseEntity<Object> response = restApiCaller.exchange(workingDirectory + getUrl, HttpMethod.POST, requestEntity, Object.class);
            return response.getBody();
        } catch (Exception e) {
            throw new CustomExceptionHandler();
        }
    }

    @ControllerAdvice
    public class CustomExceptionHandler extends RuntimeException {

        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(NullPointerException.class)
        public void handleException(String message) {
            System.out.println(message);
        }
    }
}
