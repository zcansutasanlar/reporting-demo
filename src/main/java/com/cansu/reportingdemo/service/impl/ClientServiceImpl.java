package com.cansu.reportingdemo.service.impl;

import com.cansu.reportingdemo.model.Constants;
import com.cansu.reportingdemo.model.request.UserLoginInfoRequest;
import com.cansu.reportingdemo.model.response.UserLoginInfoResponse;
import com.cansu.reportingdemo.service.ClientService;
import com.cansu.reportingdemo.service.MerchantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private final RestTemplate restApiCaller;

    public UserLoginInfoResponse login(UserLoginInfoRequest userLoginInfoRequest) {
        String merchantUserLoginURL = (Constants.workingDirectory.equalsIgnoreCase("LIVE") ? Constants.workingURL : Constants.testingURL) + "/api/v3/merchant/user/login";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("apiKey", "apiKey");

            HttpEntity requestEntity = new HttpEntity(userLoginInfoRequest, headers);
            ResponseEntity<UserLoginInfoResponse> response = restApiCaller.exchange(merchantUserLoginURL, HttpMethod.POST, requestEntity, UserLoginInfoResponse.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean getClient(String transactionId) {
        return false;
    }
}
