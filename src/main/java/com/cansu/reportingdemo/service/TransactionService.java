package com.cansu.reportingdemo.service;

import com.cansu.reportingdemo.model.request.UserLoginInfoRequest;
import com.cansu.reportingdemo.model.response.UserLoginInfoResponse;

public interface TransactionService {

    UserLoginInfoResponse transactionsReport(UserLoginInfoRequest userLoginInfoRequest);
    UserLoginInfoResponse transactionList(UserLoginInfoRequest userLoginInfoRequest);
    UserLoginInfoResponse transaction(UserLoginInfoRequest userLoginInfoRequest);
}
