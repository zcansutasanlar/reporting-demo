package com.cansu.reportingdemo.service;

import com.cansu.reportingdemo.model.request.UserLoginInfoRequest;

public interface ClientService {

    boolean login(UserLoginInfoRequest userLoginInfoRequest);
}
