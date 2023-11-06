package com.cansu.reportingdemo;

import com.cansu.reportingdemo.controller.MerchantController;
import com.cansu.reportingdemo.model.Constants;
import com.cansu.reportingdemo.model.request.UserLoginInfoRequest;
import com.cansu.reportingdemo.service.rest.RestResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ReportingDemoApplicationTests {


	Constants constants;

	@Autowired
	MerchantController merchantController;
	@Test
	void contextLoads() {
		constants.workingDirectory = "TEST";
	}

	@Test
	void loginReportingSystem() {
		/*UserLoginInfoRequest request = new UserLoginInfoRequest();
		request.setEmail("demo@financialhouse.io");
		request.setPassword("cjaiU8CV");

		RestResponse<Object> response  = merchantController.login(request);
		assertNotNull(response);

*/
	}

}
