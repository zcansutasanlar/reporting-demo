package com.cansu.reportingdemo.service.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestResponse<T> {

    private Status status;
    private T data;
    private Object errors;
    private Object metadata;

    public static <T> RestResponse<T> ok() {
        RestResponse<T> response = new RestResponse<>();
        response.setStatus(Status.OK);
        return response;
    }

    public static <T> RestResponse<T> error() {
        RestResponse<T> response = new RestResponse<>();
        response.setStatus(Status.DECLINED);
        return response;
    }

    public enum Status {
        OK, DECLINED
    }

    @Getter
    @Accessors(chain = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PageMetadata {
        private final int size;
        private final long totalElements;
        private final int totalPages;
        private final int number;

        public PageMetadata(int size, long totalElements, int totalPages, int number) {
            this.size = size;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
            this.number = number;
        }
    }

}