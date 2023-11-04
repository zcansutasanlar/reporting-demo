package com.cansu.reportingdemo.controller;

import com.cansu.reportingdemo.model.Constants;
import com.cansu.reportingdemo.model.request.UserLoginInfoRequest;
import com.cansu.reportingdemo.service.ClientService;
import com.cansu.reportingdemo.service.TransactionService;
import com.cansu.reportingdemo.service.rest.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/client", produces = {MediaType.APPLICATION_JSON_VALUE +";charset=UTF-8"})
public class ClientController {
    /* ALL POST MAPPINGS
    /api/v3/client --- Request for information of client.

     */

    private final ClientService clientService;

    @GetMapping(value = "/getClient/transactionId={transactionId}")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<Object> getClient(@Valid @PathVariable(name = "transactionId") String transactionId) throws Exception {
        return RestResponse.ok();
    }


}
