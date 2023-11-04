package com.cansu.reportingdemo.service.impl;

import com.cansu.reportingdemo.model.Constants;
import com.cansu.reportingdemo.model.request.UserLoginInfoRequest;
import com.cansu.reportingdemo.model.response.UserLoginInfoResponse;
import com.cansu.reportingdemo.service.MerchantService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private final RestTemplate restApiCaller;
    Constants constants;

    //private final String merchantUserLoginURL = constants.workingURL + "/api/v3/merchant/user/login";
    private final String merchantUserLoginURL = constants.testingURL + "/api/v3/merchant/user/login";

    public UserLoginInfoResponse login(UserLoginInfoRequest userLoginInfoRequest) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("apiKey","apiKey");

            HttpEntity requestEntity = new HttpEntity(userLoginInfoRequest,headers);
            ResponseEntity<UserLoginInfoResponse> response = restApiCaller.exchange(merchantUserLoginURL, HttpMethod.POST, requestEntity,UserLoginInfoResponse.class );
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
