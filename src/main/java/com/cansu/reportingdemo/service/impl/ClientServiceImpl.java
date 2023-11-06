package com.cansu.reportingdemo.service.impl;

import com.cansu.reportingdemo.model.Constants;
import com.cansu.reportingdemo.model.request.GetTransactionRequest;
import com.cansu.reportingdemo.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final RestTemplate restApiCaller;

    @Override
    public Object getClient(GetTransactionRequest request) {
        String transactionURL = (Constants.workingDirectory.equalsIgnoreCase("LIVE") ? Constants.workingURL : Constants.testingURL) + "/api/v3/client";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", Constants.token);
            HttpEntity requestEntity = new HttpEntity(request, headers);
            ResponseEntity<Object> response = restApiCaller.exchange(transactionURL, HttpMethod.POST, requestEntity, Object.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
