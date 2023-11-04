package com.cansu.reportingdemo.service.impl;

import com.cansu.reportingdemo.model.Constants;
import com.cansu.reportingdemo.model.request.UserLoginInfoRequest;
import com.cansu.reportingdemo.model.response.UserLoginInfoResponse;
import com.cansu.reportingdemo.service.TransactionService;
import com.cansu.reportingdemo.service.rest.RestClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class TransactionServiceImpl implements TransactionService {

    RestClient restApiCaller;
    Constants constants;

    private final String transactionsReportURL = constants.workingURL + "/api/v3/transactions/report";
    private final String transactionListURL = constants.workingURL + "/api/v3/transaction/list";
    private final String transactionURL = constants.workingURL + "/api/v3/transaction";

    @Override
    public UserLoginInfoResponse transactionsReport(UserLoginInfoRequest request) {
        try {
            HttpEntity requestEntity = new HttpEntity(request,new HttpHeaders());
            ResponseEntity<UserLoginInfoResponse> response = restApiCaller.exchange(transactionsReportURL, HttpMethod.POST, requestEntity,UserLoginInfoResponse.class );
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserLoginInfoResponse transactionList(UserLoginInfoRequest request) {
        try {
            HttpEntity requestEntity = new HttpEntity(request,new HttpHeaders());
            ResponseEntity<UserLoginInfoResponse> response = restApiCaller.exchange(transactionListURL, HttpMethod.POST, requestEntity,UserLoginInfoResponse.class );
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserLoginInfoResponse transaction(UserLoginInfoRequest request) {
        try {
            HttpEntity requestEntity = new HttpEntity(request,new HttpHeaders());
            ResponseEntity<UserLoginInfoResponse> response = restApiCaller.exchange(transactionURL, HttpMethod.POST, requestEntity,UserLoginInfoResponse.class );
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
