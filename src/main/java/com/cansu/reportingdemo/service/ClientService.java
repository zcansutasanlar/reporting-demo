package com.cansu.reportingdemo.service;

import com.cansu.reportingdemo.model.request.UserLoginInfo;

public interface ClientService {

    boolean login(UserLoginInfo userLoginInfo);
}
