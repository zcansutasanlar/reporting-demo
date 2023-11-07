package com.cansu.reportingdemo;

import com.cansu.reportingdemo.controller.ClientController;
import com.cansu.reportingdemo.controller.MerchantController;
import com.cansu.reportingdemo.controller.TransactionController;
import com.cansu.reportingdemo.model.request.GetTransactionRequest;
import com.cansu.reportingdemo.model.request.TransactionQueryRequest;
import com.cansu.reportingdemo.model.request.TransactionReportRequest;
import com.cansu.reportingdemo.model.request.UserLoginInfoRequest;
import com.cansu.reportingdemo.model.response.GetTransactionResponse;
import com.cansu.reportingdemo.model.response.TransactionReportResponse;
import com.cansu.reportingdemo.model.response.UserLoginInfoResponse;
import com.cansu.reportingdemo.service.rest.RestResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@PropertySource("application.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReportingDemoApplicationTests {

	@Autowired
	MerchantController merchantController;

	private String auth;

	@Autowired
	TransactionController transactionController;

	@Autowired
	ClientController clientController;


	@Test
	@BeforeAll
	void loginReportingSystem() {
		UserLoginInfoRequest request = UserLoginInfoRequest
				.builder()
				.email("demo@financialhouse.io")
				.password("cjaiU8CV").build();
		RestResponse<Object> response  = merchantController.login(request);
		auth = ((UserLoginInfoResponse)response.getData()).getToken();
		assertNotNull(((UserLoginInfoResponse)response.getData()).getToken());
	}

	@Test
	void transactionReportTest() throws ParseException {
		TransactionReportRequest request = TransactionReportRequest
				.builder()
				.fromDate(new SimpleDateFormat("yyyy-MM-dd").parse("2015-07-01"))
				.toDate(new SimpleDateFormat("yyyy-MM-dd").parse("2023-10-01"))
				.build();
		RestResponse<Object> response  = transactionController.transactionsReport(auth,request);
		assertEquals(5,((TransactionReportResponse)response.getData()).getResponse().stream().collect(Collectors.toList()).size());
	}

	@Test
	void transactionQueryTest() throws ParseException {
		TransactionQueryRequest request = TransactionQueryRequest
				.builder()
				.fromDate(new SimpleDateFormat("yyyy-MM-dd").parse("2015-07-01"))
				.build();
		ResponseEntity<Object> response  = transactionController.transactionList(auth,request);
		System.out.println(response);
		assertNotEquals(500,response.getStatusCode());
		assertNotEquals(400,response.getStatusCode());
		assert("200 OK".equalsIgnoreCase(response.getStatusCode().toString()));
	}

	@Test
	void transactionGetTest() {
		GetTransactionRequest request = GetTransactionRequest
				.builder()
				.transactionId("1067301-1675430916-3")
				.build();
		RestResponse<Object> response  = transactionController.getTransaction(auth,request);
		System.out.println(response);
		assertNotNull( response.getData());
	}

	@Test
	void clientGetTest() {
		GetTransactionRequest request = GetTransactionRequest
				.builder()
				.transactionId("1067301-1675430916-3")
				.build();
		RestResponse<Object> response  = clientController.getClient(auth,request);
		System.out.println(response);
		assertNotNull( response.getData());
	}
}
