package com.packtpub.springrest.client.internal;

import com.packtpub.springrest.client.ClientException;
import com.packtpub.springrest.client.internal.ApiResponse.ApiError;
import com.packtpub.springrest.client.internal.ApiResponse.Status;
import org.springframework.web.client.HttpClientErrorException;

import java.util.concurrent.Callable;

/**
 * This utility class handles responses from the backend.
 *
 * @author Ludovic Dewailly
 */
public class ResponseHandler {

    public static Object handle(Callable<ApiResponse> invocation) {
        if (invocation == null) {
            throw new IllegalArgumentException("invocation cannot be null");
        }
        try{
            ApiResponse response = invocation.call();
            if (response.getStatus() == Status.ERROR) {
                throw new ClientException(response.getError());
            }
            return response.getData();
        } catch (HttpClientErrorException e) {
            throw new ClientException(new ApiError(e.getStatusCode().value(), e.getMessage()));
        } catch (Exception e) {
            throw new ClientException(new ApiError(0, e.toString()));
        }
    }
}
