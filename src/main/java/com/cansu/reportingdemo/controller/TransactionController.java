package com.cansu.reportingdemo.controller;

import com.cansu.reportingdemo.model.Constants;
import com.cansu.reportingdemo.model.request.UserLoginInfo;
import com.cansu.reportingdemo.service.RestApiCaller;
import com.cansu.reportingdemo.service.TransactionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    /* ALL POST MAPPINGS
    /api/v3/transactions/report --- Request for list of transaction.
    /api/v3/transaction/list --- Request for list of transaction.
    /api/v3/transaction --- Request for all information of transaction.

     */

    TransactionService transactionService;

    @PostMapping(value = "/transactionsReport" , consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Boolean transactionsReport( @RequestBody(required = true) UserLoginInfo request) {
        transactionService.transactionsReport(request);
        return Boolean.TRUE;
    }

    @PostMapping(value = "/transactionList" , consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Boolean transactionList( @RequestBody(required = true) UserLoginInfo request) {
        transactionService.transactionList(request);
        return Boolean.TRUE;
    }

    @PostMapping(value = "/transaction" , consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Boolean transaction( @RequestBody(required = true) UserLoginInfo request) {
        transactionService.transaction(request);
        return Boolean.TRUE;
    }

}
