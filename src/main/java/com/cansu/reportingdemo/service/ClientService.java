package com.cansu.reportingdemo.service;

import com.cansu.reportingdemo.model.request.GetTransactionRequest;

public interface ClientService {

    Object getClient(GetTransactionRequest transactionId);
}
