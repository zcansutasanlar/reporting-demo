package com.cansu.reportingdemo.service;

import com.cansu.reportingdemo.model.request.TransactionQueryRequest;
import com.cansu.reportingdemo.model.request.TransactionReportRequest;
import com.cansu.reportingdemo.model.response.TransactionQueryResponse;
import com.cansu.reportingdemo.model.response.TransactionReportResponse;
import com.cansu.reportingdemo.model.response.UserLoginInfoResponse;

public interface TransactionService {

    TransactionReportResponse transactionsReport(TransactionReportRequest userLoginInfoRequest);
    TransactionQueryResponse transactionList(TransactionQueryRequest userLoginInfoRequest);
    UserLoginInfoResponse transaction(String transactionId);
}
