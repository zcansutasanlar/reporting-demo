package com.cansu.reportingdemo.service.impl;

import com.cansu.reportingdemo.model.Constants;
import com.cansu.reportingdemo.model.request.GetTransactionRequest;
import com.cansu.reportingdemo.model.request.TransactionQueryRequest;
import com.cansu.reportingdemo.model.request.TransactionReportRequest;
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

    private final RestTemplate restApiCaller;

    @Override
    public TransactionReportResponse transactionsReport(TransactionReportRequest request) {
        String transactionsReportURL = (Constants.workingDirectory.equalsIgnoreCase("LIVE") ? Constants.workingURL : Constants.testingURL) + "/api/v3/transactions/report";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization",Constants.token);
            HttpEntity requestEntity = new HttpEntity(request,headers);
            ResponseEntity<TransactionReportResponse> response = restApiCaller.exchange(transactionsReportURL, HttpMethod.POST, requestEntity,TransactionReportResponse.class );
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResponseEntity<Object> transactionList(TransactionQueryRequest request) {
        String transactionListURL = (Constants.workingDirectory.equalsIgnoreCase("LIVE") ? Constants.workingURL : Constants.testingURL) + "/api/v3/transaction/list";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization",Constants.token);
            HttpEntity requestEntity = new HttpEntity(request,headers);
            ResponseEntity<Object> response = restApiCaller.exchange(transactionListURL, HttpMethod.POST, requestEntity,Object.class);
            System.out.println(response);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResponseEntity<Object> getTransaction(GetTransactionRequest request) {
        String transactionURL = (Constants.workingDirectory.equalsIgnoreCase("LIVE") ? Constants.workingURL : Constants.testingURL) + "/api/v3/transaction";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization",Constants.token);
            HttpEntity requestEntity = new HttpEntity(request,headers);
            ResponseEntity<Object> response = restApiCaller.exchange(transactionURL, HttpMethod.POST, requestEntity,Object.class );
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
