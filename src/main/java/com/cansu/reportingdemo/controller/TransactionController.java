package com.cansu.reportingdemo.controller;

import com.cansu.reportingdemo.model.request.UserLoginInfoRequest;
import com.cansu.reportingdemo.service.TransactionService;
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
    public Boolean transactionsReport( @RequestBody(required = true) UserLoginInfoRequest request) {
        transactionService.transactionsReport(request);
        return Boolean.TRUE;
    }

    @PostMapping(value = "/transactionList" , consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Boolean transactionList( @RequestBody(required = true) UserLoginInfoRequest request) {
        transactionService.transactionList(request);
        return Boolean.TRUE;
    }

    @PostMapping(value = "/transaction" , consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Boolean transaction( @RequestBody(required = true) UserLoginInfoRequest request) {
        transactionService.transaction(request);
        return Boolean.TRUE;
    }

}
