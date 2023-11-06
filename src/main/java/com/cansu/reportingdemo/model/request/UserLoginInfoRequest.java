package com.cansu.reportingdemo.model.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class UserLoginInfoRequest {
    @NotBlank String email;
    @NotBlank String password;
}
