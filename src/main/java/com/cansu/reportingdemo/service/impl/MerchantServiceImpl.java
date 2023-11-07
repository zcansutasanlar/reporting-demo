package com.cansu.reportingdemo.service.impl;

import com.cansu.reportingdemo.model.request.UserLoginInfoRequest;
import com.cansu.reportingdemo.model.response.UserLoginInfoResponse;
import com.cansu.reportingdemo.service.MerchantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {

    private final RestTemplate restApiCaller;

    @Value("${report.workingDirectory.url:https://sandbox-reporting.rpdpymnt.com}")
    String workingDirectory;

    @Value("${report.merchant.login.url:/api/v3/merchant/user/login?apiKey=apiKey}")
    String loginUrl;

    public UserLoginInfoResponse login(UserLoginInfoRequest userLoginInfoRequest) {
        try {
            HttpEntity requestEntity = new HttpEntity(userLoginInfoRequest, new HttpHeaders());
            ResponseEntity<UserLoginInfoResponse> response = restApiCaller.exchange(workingDirectory + loginUrl, HttpMethod.POST, requestEntity, UserLoginInfoResponse.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
