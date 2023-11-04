package com.cansu.reportingdemo.service;

import com.cansu.reportingdemo.model.request.UserLoginInfoRequest;
import com.cansu.reportingdemo.model.response.UserLoginInfoResponse;

public interface MerchantService {

    UserLoginInfoResponse login(UserLoginInfoRequest userLoginInfoRequest);
}
