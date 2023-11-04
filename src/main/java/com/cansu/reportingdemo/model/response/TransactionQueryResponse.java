package com.cansu.reportingdemo.model.response;

import com.fasterxml.jackson.databind.util.JSONPObject;


public class TransactionQueryResponse {
    Integer per_page;
    Integer current_page;
    String next_page_url;
    String prev_page_url;
    Integer from;
    Integer to;
    JSONPObject data;

}
