package com.cansu.reportingdemo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@UtilityClass
@Getter
@Setter
public class Constants {
    public String workingDirectory = "TEST";

    public String workingURL = "https://reporting.rpdpymnt.com";
    public String testingURL = "https://sandbox-reporting.rpdpymnt.com";

    public String token = "";
}
