package com.cansu.reportingdemo.service;

import com.cansu.reportingdemo.model.request.UserLoginInfo;

public interface TransactionService {

    boolean transactionsReport(UserLoginInfo userLoginInfo);
    boolean transactionList(UserLoginInfo userLoginInfo);
    boolean transaction(UserLoginInfo userLoginInfo);
}
