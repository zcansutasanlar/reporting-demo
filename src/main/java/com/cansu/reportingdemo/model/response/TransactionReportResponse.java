package com.cansu.reportingdemo.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TransactionReportResponse {

    String status;
    List<Object> response;

}
