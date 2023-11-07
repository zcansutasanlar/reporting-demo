package com.cansu.reportingdemo.model.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
public class TransactionReportRequest {
    @NotNull Date fromDate;
    @NotNull Date toDate;
    Integer merchant;
    Integer acquirer;
}

