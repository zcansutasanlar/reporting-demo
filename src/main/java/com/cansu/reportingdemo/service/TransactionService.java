package com.cansu.reportingdemo.service;

import com.cansu.reportingdemo.model.request.GetTransactionRequest;
import com.cansu.reportingdemo.model.request.TransactionQueryRequest;
import com.cansu.reportingdemo.model.request.TransactionReportRequest;
import com.cansu.reportingdemo.model.response.GetTransactionResponse;
import com.cansu.reportingdemo.model.response.TransactionReportResponse;

public interface TransactionService {

    TransactionReportResponse transactionsReport(TransactionReportRequest request);

    Object transactionList(TransactionQueryRequest request);

    GetTransactionResponse getTransaction(GetTransactionRequest request);
}
