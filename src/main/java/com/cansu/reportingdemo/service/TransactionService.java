package com.cansu.reportingdemo.service;

import com.cansu.reportingdemo.model.request.GetTransactionRequest;
import com.cansu.reportingdemo.model.request.TransactionQueryRequest;
import com.cansu.reportingdemo.model.request.TransactionReportRequest;
import com.cansu.reportingdemo.model.response.TransactionReportResponse;
import com.cansu.reportingdemo.model.response.UserLoginInfoResponse;
import org.springframework.http.ResponseEntity;

public interface TransactionService {

    TransactionReportResponse transactionsReport(TransactionReportRequest userLoginInfoRequest);
    ResponseEntity<Object> transactionList(TransactionQueryRequest userLoginInfoRequest);
    ResponseEntity<Object> getTransaction(GetTransactionRequest transactionId);
}
