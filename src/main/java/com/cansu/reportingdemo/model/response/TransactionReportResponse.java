package com.cansu.reportingdemo.model.response;

import java.util.List;

class Response{
    Integer count;
    Integer total;
    String currency;
}
public class TransactionReportResponse {

    String status;
    List<Response> response;

}
