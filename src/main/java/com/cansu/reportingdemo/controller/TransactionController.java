package com.cansu.reportingdemo.controller;

import com.cansu.reportingdemo.model.request.UserLoginInfoRequest;
import com.cansu.reportingdemo.service.TransactionService;
import com.cansu.reportingdemo.service.rest.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    /* ALL POST MAPPINGS
    /api/v3/transactions/report --- Request for list of transaction.
    /api/v3/transaction/list --- Request for list of transaction.
    /api/v3/transaction --- Request for all information of transaction.

     */

    TransactionService transactionService;

    @PostMapping(value = "/transactionsReport" , consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<Object> transactionsReport(@RequestBody(required = true) UserLoginInfoRequest request) {
        return RestResponse.ok().setData(transactionService.transactionsReport(request));
    }

    @PostMapping(value = "/transactionList" , consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<Object>  transactionList( @RequestBody(required = true) UserLoginInfoRequest request) {
        return RestResponse.ok().setData(transactionService.transactionList(request));
    }

    @PostMapping(value = "/transaction" , consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<Object>  transaction( @RequestBody(required = true) UserLoginInfoRequest request) {
        return RestResponse.ok().setData(transactionService.transaction(request));
    }

}
