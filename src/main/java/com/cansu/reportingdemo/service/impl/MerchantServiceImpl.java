package com.cansu.reportingdemo.service.impl;

import com.cansu.reportingdemo.model.Constants;
import com.cansu.reportingdemo.model.request.UserLoginInfoRequest;
import com.cansu.reportingdemo.model.response.UserLoginInfoResponse;
import com.cansu.reportingdemo.service.MerchantService;

import com.smattme.requestvalidator.RequestValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MerchantServiceImpl implements MerchantService {

    RestTemplate restApiCaller;
    Constants constants;

    private final String merchantUserLoginURL = constants.workingURL + "/api/v3/merchant/user/login";

    public UserLoginInfoResponse login(UserLoginInfoRequest userLoginInfoRequest) {
        Map<String, String> rules = new HashMap<>();
        rules.put("email", "required|email");
        rules.put("password", "required");

        List<String> errors = RequestValidator.validate(userLoginInfoRequest, rules);
        if (!errors.isEmpty()) {
            return null;
        }


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
