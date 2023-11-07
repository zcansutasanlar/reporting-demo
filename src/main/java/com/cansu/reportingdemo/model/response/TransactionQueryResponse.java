package com.cansu.reportingdemo.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TransactionQueryResponse {
    Integer per_page;
    Integer current_page;
    String next_page_url;
    String prev_page_url;
    Integer from;
    Integer to;
    List<Object> data;

}
