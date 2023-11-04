package com.cansu.reportingdemo.controller;

import com.cansu.reportingdemo.model.request.UserLoginInfoRequest;
import com.cansu.reportingdemo.model.response.UserLoginInfoResponse;
import com.cansu.reportingdemo.service.MerchantService;
import com.cansu.reportingdemo.service.rest.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchant")
public class MerchantController {
    /* ALL POST MAPPINGS
    /api/v3/merchant/user/login --- Login with email and password.

     */

    MerchantService merchantService;

    @PostMapping(value = "/login" , consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<Object> login(@RequestBody(required = true) UserLoginInfoRequest request) {
        return RestResponse.ok().setData(merchantService.login(request));
    }


}
