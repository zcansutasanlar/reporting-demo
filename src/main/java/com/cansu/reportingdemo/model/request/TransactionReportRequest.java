package com.cansu.reportingdemo.model.request;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TransactionReportRequest {
    Date fromDate;
    Date toDate;
    Integer merchant;
    Integer acquirer;
}

