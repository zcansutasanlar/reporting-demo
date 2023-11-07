package com.cansu.reportingdemo.service;

import com.cansu.reportingdemo.model.request.GetTransactionRequest;
import com.cansu.reportingdemo.model.request.TransactionQueryRequest;
import com.cansu.reportingdemo.model.request.TransactionReportRequest;
import com.cansu.reportingdemo.model.response.GetTransactionResponse;
import com.cansu.reportingdemo.model.response.TransactionReportResponse;
import org.springframework.http.ResponseEntity;

public interface TransactionService {

    TransactionReportResponse transactionsReport(String authToken, TransactionReportRequest request);

    ResponseEntity<Object> transactionList(String authToken, TransactionQueryRequest request);

    GetTransactionResponse getTransaction(String authToken,GetTransactionRequest request);
}
