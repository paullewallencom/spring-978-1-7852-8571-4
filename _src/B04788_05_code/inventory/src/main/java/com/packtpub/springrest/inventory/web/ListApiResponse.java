package com.packtpub.springrest.inventory.web;

import com.packtpub.springrest.inventory.web.ApiResponse.ApiError;
import com.packtpub.springrest.inventory.web.ApiResponse.Status;

import java.util.List;

/**
 * Created by ldewailly on 15/05/19.
 */
public class ListApiResponse {

    private final Status status;
    private final List<Object> data;
    private final ApiError error;
    private final int pageNumber;
    private final String nextPage;
    private final long total;

    public ListApiResponse(Status status, List<Object> data, ApiError error, int pageNumber, String nextPage,
                           long total) {
        this.status = status;
        this.data = data;
        this.error = error;
        this.pageNumber = pageNumber;
        this.nextPage = nextPage;
        this.total = total;
    }

    public Status getStatus() {
        return status;
    }

    public List<Object> getData() {
        return data;
    }

    public ApiError getError() {
        return error;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public String getNextPage() {
        return nextPage;
    }

    public long getTotal() {
        return total;
    }
}
