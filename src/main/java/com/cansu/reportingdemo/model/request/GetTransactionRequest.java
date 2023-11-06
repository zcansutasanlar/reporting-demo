package com.cansu.reportingdemo.model.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class GetTransactionRequest {
    String transactionId;
}
