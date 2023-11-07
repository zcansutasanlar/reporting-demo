package com.cansu.reportingdemo.controller;

import com.cansu.reportingdemo.model.request.GetTransactionRequest;
import com.cansu.reportingdemo.model.request.TransactionQueryRequest;
import com.cansu.reportingdemo.model.request.TransactionReportRequest;
import com.cansu.reportingdemo.service.TransactionService;
import com.cansu.reportingdemo.service.rest.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "${report.ui.crossOrigin.url}")
@RequestMapping(value = "/transaction", produces = {MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"})
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping(value = "/report", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<Object> transactionsReport(@Valid @RequestHeader("Authorization") String authToken, @RequestBody TransactionReportRequest request) {
        return RestResponse.ok().setData(transactionService.transactionsReport(authToken, request));
    }

    @PostMapping(value = "/query", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> transactionList(@Valid @RequestHeader("Authorization") String authToken, @RequestBody TransactionQueryRequest request) {
        return transactionService.transactionList(authToken, request);
    }

    @PostMapping(value = "/get", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<Object> getTransaction(@Valid @RequestHeader("Authorization") String authToken, @RequestBody GetTransactionRequest request) {
        return RestResponse.ok().setData(transactionService.getTransaction(authToken, request));
    }

}
