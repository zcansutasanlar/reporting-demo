package com.cansu.reportingdemo.controller;

import com.cansu.reportingdemo.model.request.UserLoginInfoRequest;
import com.cansu.reportingdemo.model.response.UserLoginInfoResponse;
import com.cansu.reportingdemo.service.MerchantService;
import com.cansu.reportingdemo.service.rest.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "${report.ui.crossOrigin.url:http://localhost:3000}")
@RequestMapping(value = "/api/merchant", produces = {MediaType.APPLICATION_JSON_VALUE +";charset=UTF-8"})
public class MerchantController {

    private final MerchantService merchantService;

    @PostMapping(value = "/login" , consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<Object> login(@Valid @RequestBody UserLoginInfoRequest request) {
        UserLoginInfoResponse resp = merchantService.login(request);
        if (resp == null || !resp.getStatus().equalsIgnoreCase("APPROVED")) {
            return RestResponse.error().setData(resp);
        } else
            return RestResponse.ok().setData(resp);

    }


}
