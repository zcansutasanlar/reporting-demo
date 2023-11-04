package com.cansu.reportingdemo.service;

import com.cansu.reportingdemo.model.request.UserLoginInfoRequest;

public interface TransactionService {

    boolean transactionsReport(UserLoginInfoRequest userLoginInfoRequest);
    boolean transactionList(UserLoginInfoRequest userLoginInfoRequest);
    boolean transaction(UserLoginInfoRequest userLoginInfoRequest);
}
