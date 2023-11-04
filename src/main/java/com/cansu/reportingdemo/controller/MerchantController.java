package com.cansu.reportingdemo.controller;

import com.cansu.reportingdemo.model.request.UserLoginInfoRequest;
import com.cansu.reportingdemo.service.MerchantService;
import com.cansu.reportingdemo.service.rest.RestResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/merchant", produces = {MediaType.APPLICATION_JSON_VALUE +";charset=UTF-8"})
@Api(value = "merchant-api")
public class MerchantController {

    private final MerchantService merchantService;

    @PostMapping(value = "/login" , consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<Object> login(@RequestBody(required = true) UserLoginInfoRequest request) {
        return RestResponse.ok().setData(merchantService.login(request));
    }


}
