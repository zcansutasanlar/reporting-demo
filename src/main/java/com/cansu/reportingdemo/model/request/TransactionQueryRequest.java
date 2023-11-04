package com.cansu.reportingdemo.model.request;

import java.util.Date;

public class TransactionQueryRequest {
    Date fromDate;
    Date toDate;
    String status;
    String operation;
    Integer merchantId;
    Integer acquirerId;
    String paymentMethod;
    String errorCode;
    String filterField;
    String filterValue;
    Integer page;

}







