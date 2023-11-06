package com.cansu.reportingdemo.controller;

import com.cansu.reportingdemo.model.request.GetTransactionRequest;
import com.cansu.reportingdemo.service.ClientService;
import com.cansu.reportingdemo.service.rest.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/client", produces = {MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"})
public class ClientController {
    /* ALL POST MAPPINGS
    /api/v3/client --- Request for information of client.

     */

    private final ClientService clientService;

    @PostMapping(value = "/get", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<Object> getTransaction(@Valid @RequestBody GetTransactionRequest request) {
        return RestResponse.ok().setData(clientService.getClient(request));
    }


}
