package com.cansu.reportingdemo.service.impl;

import com.cansu.reportingdemo.model.Constants;
import com.cansu.reportingdemo.model.request.UserLoginInfo;
import com.cansu.reportingdemo.service.MerchantService;
import com.cansu.reportingdemo.service.RestApiCaller;
import org.springframework.http.HttpHeaders;

public class MerchantServiceImpl implements MerchantService {

    RestApiCaller restApiCaller;
    Constants constants;

    private final String merchantUserLoginURL = constants.workingURL + "/api/v3/merchant/user/login";

    @Override
    public boolean login(UserLoginInfo userLoginInfo) {
        try {
            restApiCaller.postRequest(merchantUserLoginURL, String.class, userLoginInfo, new HttpHeaders());
            return Boolean.TRUE;
        } catch (Exception e) {
            return false;
        }
    }

}
