package com.cansu.reportingdemo.controller;

import com.cansu.reportingdemo.model.Constants;
import com.cansu.reportingdemo.model.request.UserLoginInfo;
import com.cansu.reportingdemo.service.RestApiCaller;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reporter")
public class ClientController {
    /* ALL POST MAPPINGS
    /api/v3/client --- Request for information of client.

     */

    RestApiCaller restApiCaller;
    Constants constants;

    @PostMapping(value = "/login" , consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Boolean login( @RequestBody(required = true) UserLoginInfo userLoginInfo) throws Exception {
        restApiCaller.postRequest(constants.workingURL + "/api/v3/merchant/user/login", String.class, userLoginInfo, new HttpHeaders());
        return Boolean.TRUE;
    }


}
