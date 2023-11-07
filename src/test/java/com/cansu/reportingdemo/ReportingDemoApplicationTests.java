package com.cansu.reportingdemo;

import com.cansu.reportingdemo.controller.MerchantController;
import com.cansu.reportingdemo.controller.TransactionController;
import com.cansu.reportingdemo.model.Constants;
import com.cansu.reportingdemo.model.request.TransactionReportRequest;
import com.cansu.reportingdemo.model.request.UserLoginInfoRequest;
import com.cansu.reportingdemo.model.response.UserLoginInfoResponse;
import com.cansu.reportingdemo.service.rest.RestResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ReportingDemoApplicationTests {


	Constants constants;

	@Autowired
	MerchantController merchantController;

	@Autowired
	TransactionController transactionController;

	@Test
	void contextLoads() {
		constants.workingDirectory = "TEST";
	}

	String auth ;

	@Test
	@BeforeAll
	void loginReportingSystem() {
		UserLoginInfoRequest request = UserLoginInfoRequest
				.builder()
				.email("demo@financialhouse.io")
				.password("cjaiU8CV").build();
		RestResponse<Object> response  = merchantController.login(request);
		System.out.println(((UserLoginInfoResponse)response.getData()).getToken());
		assertNotNull(response);
		auth = ((UserLoginInfoResponse)response.getData()).getToken();
	}

	@Test
	void transactionReportTest() throws ParseException {
		TransactionReportRequest request = TransactionReportRequest
				.builder()
				.fromDate(new SimpleDateFormat("yyyy-MM-dd").parse("2015-07-01"))
				.toDate(new SimpleDateFormat("yyyy-MM-dd").parse("2023-10-01"))
				.build();
		RestResponse<Object> response  = transactionController.transactionsReport("",request);
		System.out.println(((UserLoginInfoResponse)response.getData()).getToken());
		assertNotNull(response);
	}
}
