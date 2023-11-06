package com.cansu.reportingdemo.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserLoginInfoResponse {
    String token;
    String status;
}
