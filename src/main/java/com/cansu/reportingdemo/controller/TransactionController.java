package com.cansu.reportingdemo.controller;

import com.cansu.reportingdemo.model.request.GetTransactionRequest;
import com.cansu.reportingdemo.model.request.TransactionQueryRequest;
import com.cansu.reportingdemo.model.request.TransactionReportRequest;
import com.cansu.reportingdemo.service.TransactionService;
import com.cansu.reportingdemo.service.rest.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/transaction", produces = {MediaType.APPLICATION_JSON_VALUE +";charset=UTF-8"})
public class TransactionController {
    /* ALL POST MAPPINGS
    /api/v3/transactions/report --- Request for list of transaction.
    /api/v3/transaction/list --- Request for list of transaction.
    /api/v3/transaction --- Request for all information of transaction.

     */

    private final TransactionService transactionService;

    @PostMapping(value = "/transactionsReport" , consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<Object> transactionsReport(@Valid @RequestBody(required = true) TransactionReportRequest request) {
        return RestResponse.ok().setData(transactionService.transactionsReport(request));
    }

    @PostMapping(value = "/transactionList" , consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<Object>  transactionList(@Valid @RequestBody(required = true) TransactionQueryRequest request) {
        return RestResponse.ok().setData(transactionService.transactionList(request));
    }

    @PostMapping(value = "/getTransaction" , consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<Object> getTransaction(@Valid @RequestBody(required = true) GetTransactionRequest transactionId) {
        return RestResponse.ok().setData(transactionService.getTransaction(transactionId));
    }

}
