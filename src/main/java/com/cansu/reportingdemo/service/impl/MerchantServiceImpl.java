package com.cansu.reportingdemo.service.impl;

import com.cansu.reportingdemo.model.Constants;
import com.cansu.reportingdemo.model.request.UserLoginInfoRequest;
import com.cansu.reportingdemo.model.response.UserLoginInfoResponse;
import com.cansu.reportingdemo.service.MerchantService;
import com.cansu.reportingdemo.service.RestApiCaller;
import org.springframework.http.HttpHeaders;

public class MerchantServiceImpl implements MerchantService {

    RestApiCaller restApiCaller;
    Constants constants;

    private final String merchantUserLoginURL = constants.workingURL + "/api/v3/merchant/user/login";

    @Override
    public boolean login(UserLoginInfoRequest userLoginInfoRequest) {
        try {
            String response = restApiCaller.postRequest(merchantUserLoginURL, String.class, userLoginInfoRequest, new HttpHeaders());
            //UserLoginInfoResponse response = restApiCaller.postRequest(merchantUserLoginURL, String.class, userLoginInfoRequest, new HttpHeaders());
            return Boolean.TRUE;
        } catch (Exception e) {
            return false;
        }
    }

}
