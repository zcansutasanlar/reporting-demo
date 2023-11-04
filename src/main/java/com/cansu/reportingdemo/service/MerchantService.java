package com.cansu.reportingdemo.service;

import com.cansu.reportingdemo.model.request.UserLoginInfoRequest;

public interface MerchantService {

    boolean login(UserLoginInfoRequest userLoginInfoRequest);
}
