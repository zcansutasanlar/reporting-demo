package com.cansu.reportingdemo.service.impl;

import com.cansu.reportingdemo.model.Constants;
import com.cansu.reportingdemo.model.request.UserLoginInfoRequest;
import com.cansu.reportingdemo.model.response.UserLoginInfoResponse;
import com.cansu.reportingdemo.service.MerchantService;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MerchantServiceImpl implements MerchantService {

    RestTemplate restApiCaller;
    Constants constants;

    private final String merchantUserLoginURL = constants.workingURL + "/api/v3/merchant/user/login";

    @Override
    public UserLoginInfoResponse login(UserLoginInfoRequest userLoginInfoRequest) {
        try {
            HttpEntity requestEntity = new HttpEntity(userLoginInfoRequest,new HttpHeaders());
            ResponseEntity<UserLoginInfoResponse> response = restApiCaller.exchange(merchantUserLoginURL, HttpMethod.POST, requestEntity,UserLoginInfoResponse.class );
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
