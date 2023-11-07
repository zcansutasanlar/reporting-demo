package com.cansu.reportingdemo.service.impl;

import com.cansu.reportingdemo.model.request.GetTransactionRequest;
import com.cansu.reportingdemo.model.request.TransactionQueryRequest;
import com.cansu.reportingdemo.model.request.TransactionReportRequest;
import com.cansu.reportingdemo.model.response.GetTransactionResponse;
import com.cansu.reportingdemo.model.response.TransactionReportResponse;
import com.cansu.reportingdemo.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final RestTemplate restApiCaller;

    @Value("${report.workingDirectory.url:https://sandbox-reporting.rpdpymnt.com}")
    String workingDirectory;

    @Value("${report.transaction.report.url:/api/v3/transactions/report}")
    String reportUrl;

    @Value("${report.transaction.query.url:/api/v3/transaction/list}")
    String queryUrl;

    @Value("${report.transaction.get.url:/api/v3/transaction}")
    String getUrl;

    @Override
    public TransactionReportResponse transactionsReport(String authToken, TransactionReportRequest request) {

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", authToken);
            HttpEntity requestEntity = new HttpEntity(request, headers);
            ResponseEntity<TransactionReportResponse> response = restApiCaller.exchange(workingDirectory + reportUrl, HttpMethod.POST, requestEntity, TransactionReportResponse.class);
            return response.getBody();
        } catch (Exception e) {
            throw new CustomExceptionHandler();
        }
    }

    @Override
    public ResponseEntity<Object> transactionList(String authToken, TransactionQueryRequest request) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", authToken);
            HttpEntity requestEntity = new HttpEntity(request, headers);
            ResponseEntity<Object> response = restApiCaller.exchange(workingDirectory + queryUrl, HttpMethod.POST, requestEntity, Object.class);
            return response;
        } catch (Exception e) {
            throw new CustomExceptionHandler();
        }
    }

    @Override
    public GetTransactionResponse getTransaction(String authToken, GetTransactionRequest request) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", authToken);
            HttpEntity requestEntity = new HttpEntity(request, headers);
            ResponseEntity<GetTransactionResponse> response = restApiCaller.exchange(workingDirectory + getUrl, HttpMethod.POST, requestEntity, GetTransactionResponse.class);
            return response.getBody();
        } catch (Exception e) {
            throw new CustomExceptionHandler();
        }
    }


    @ControllerAdvice
    public class CustomExceptionHandler extends RuntimeException {
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(NullPointerException.class)
        public void handleException(String message) {
            System.out.println(message);
        }
    }


}
