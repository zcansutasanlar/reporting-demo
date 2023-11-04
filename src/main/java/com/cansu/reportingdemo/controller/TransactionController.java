package com.cansu.reportingdemo.controller;

import com.cansu.reportingdemo.model.request.TransactionQueryRequest;
import com.cansu.reportingdemo.model.request.TransactionReportRequest;
import com.cansu.reportingdemo.model.request.UserLoginInfoRequest;
import com.cansu.reportingdemo.service.TransactionService;
import com.cansu.reportingdemo.service.rest.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/transaction", produces = {MediaType.APPLICATION_JSON_VALUE +";charset=UTF-8"})
public class TransactionController {
    /* ALL POST MAPPINGS
    /api/v3/transactions/report --- Request for list of transaction.
    /api/v3/transaction/list --- Request for list of transaction.
    /api/v3/transaction --- Request for all information of transaction.

     */

    private final TransactionService transactionService;

    @PostMapping(value = "/transactionsReport" , consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<Object> transactionsReport(@Valid @RequestBody(required = true) TransactionReportRequest request) {
        return RestResponse.ok().setData(transactionService.transactionsReport(request));
    }

    @PostMapping(value = "/transactionList" , consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<Object>  transactionList(@Valid @RequestBody(required = true) TransactionQueryRequest request) {
        return RestResponse.ok().setData(transactionService.transactionList(request));
    }

    //GetMapping transactionId
    @GetMapping(value = "/transaction/transactionId={transactionId}")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<Object>  transaction(@Valid @PathVariable(name = "transactionId") String transactionId) {
        return RestResponse.ok().setData(transactionService.transaction(transactionId));
    }

}
