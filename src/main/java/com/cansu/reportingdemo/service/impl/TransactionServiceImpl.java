package com.cansu.reportingdemo.service.impl;

import com.cansu.reportingdemo.model.Constants;
import com.cansu.reportingdemo.model.request.UserLoginInfoRequest;
import com.cansu.reportingdemo.service.RestApiCaller;
import com.cansu.reportingdemo.service.TransactionService;
import org.springframework.http.HttpHeaders;

public class TransactionServiceImpl implements TransactionService {

    RestApiCaller restApiCaller;
    Constants constants;

    private final String transactionsReportURL = constants.workingURL + "/api/v3/transactions/report";
    private final String transactionListURL = constants.workingURL + "/api/v3/transaction/list";
    private final String transactionURL = constants.workingURL + "/api/v3/transaction";
    /*
     --- Request for list of transaction.
     --- Request for list of transaction.
     --- Request for all information of transaction.


    @Override
    public boolean login(UserLoginInfo userLoginInfo) {
        try {
            restApiCaller.postRequest( merchantUserLoginPath, String.class, userLoginInfo, new HttpHeaders());
            return Boolean.TRUE;
        } catch (Exception e) {
            return false;
        }
    }
*/
    @Override
    public boolean transactionsReport(UserLoginInfoRequest userLoginInfoRequest) {
        try {
            restApiCaller.postRequest(transactionsReportURL, String.class, userLoginInfoRequest, new HttpHeaders());
            return Boolean.TRUE;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean transactionList(UserLoginInfoRequest userLoginInfoRequest) {
        try {
            restApiCaller.postRequest(transactionListURL, String.class, userLoginInfoRequest, new HttpHeaders());
            return Boolean.TRUE;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean transaction(UserLoginInfoRequest userLoginInfoRequest) {
        try {
            restApiCaller.postRequest(transactionURL, String.class, userLoginInfoRequest, new HttpHeaders());
            return Boolean.TRUE;
        } catch (Exception e) {
            return false;
        }
    }
}
