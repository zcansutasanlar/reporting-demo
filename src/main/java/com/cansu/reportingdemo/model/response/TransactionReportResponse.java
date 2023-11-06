package com.cansu.reportingdemo.model.response;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TransactionReportResponse {

    String status;
    List<Object> response;

}
