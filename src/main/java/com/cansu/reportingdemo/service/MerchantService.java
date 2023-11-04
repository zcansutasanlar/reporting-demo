package com.cansu.reportingdemo.service;

import com.cansu.reportingdemo.model.request.UserLoginInfo;

public interface MerchantService {

    boolean login(UserLoginInfo userLoginInfo);
}
