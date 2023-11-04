package com.cansu.reportingdemo.controller;

import com.cansu.reportingdemo.model.Constants;
import com.cansu.reportingdemo.model.request.UserLoginInfoRequest;
import com.cansu.reportingdemo.service.rest.RestClient;
import com.cansu.reportingdemo.service.rest.RestResponse;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    /* ALL POST MAPPINGS
    /api/v3/client --- Request for information of client.

     */

    RestClient restApiCaller;
    Constants constants;

    @PostMapping(value = "/login" , consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<Object> login(@RequestBody(required = true) UserLoginInfoRequest request) throws Exception {
        return RestResponse.ok();
    }


}
