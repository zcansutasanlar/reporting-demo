package com.cansu.reportingdemo.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class UserLoginInfoRequest {
    @NotBlank String email;
    @NotBlank String password;
}
