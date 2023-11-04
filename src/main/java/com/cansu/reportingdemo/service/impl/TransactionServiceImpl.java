package com.cansu.reportingdemo.service.impl;

import com.cansu.reportingdemo.model.Constants;
import com.cansu.reportingdemo.model.request.TransactionQueryRequest;
import com.cansu.reportingdemo.model.request.TransactionReportRequest;
import com.cansu.reportingdemo.model.response.TransactionQueryResponse;
import com.cansu.reportingdemo.model.response.TransactionReportResponse;
import com.cansu.reportingdemo.model.response.UserLoginInfoResponse;
import com.cansu.reportingdemo.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    RestTemplate restApiCaller;

    @Override
    public TransactionReportResponse transactionsReport(TransactionReportRequest request) {
        String transactionsReportURL = (Constants.workingDirectory.equalsIgnoreCase("LIVE") ? Constants.workingURL : Constants.testingURL) + "/api/v3/transactions/report";
        try {
            HttpEntity requestEntity = new HttpEntity(request,new HttpHeaders());
            ResponseEntity<TransactionReportResponse> response = restApiCaller.exchange(transactionsReportURL, HttpMethod.POST, requestEntity,TransactionReportResponse.class );
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public TransactionQueryResponse transactionList(TransactionQueryRequest request) {
        String transactionListURL = (Constants.workingDirectory.equalsIgnoreCase("LIVE") ? Constants.workingURL : Constants.testingURL) + "/api/v3/transaction/list";
        try {
            HttpEntity requestEntity = new HttpEntity(request,new HttpHeaders());
            ResponseEntity<TransactionQueryResponse> response = restApiCaller.exchange(transactionListURL, HttpMethod.POST, requestEntity,TransactionQueryResponse.class );
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserLoginInfoResponse transaction(String transactionId) {
        String transactionURL = (Constants.workingDirectory.equalsIgnoreCase("LIVE") ? Constants.workingURL : Constants.testingURL) + "/api/v3/transaction";

        try {
            HttpEntity requestEntity = new HttpEntity(transactionId,new HttpHeaders());
            ResponseEntity<UserLoginInfoResponse> response = restApiCaller.exchange(transactionURL, HttpMethod.POST, requestEntity,UserLoginInfoResponse.class );
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
