package com.packtpub.springrest.client;

import com.packtpub.springrest.client.internal.ApiResponse.ApiError;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClientExceptionTest {

    @Test
    public void testGetMessage() throws Exception {
        ClientException exception = new ClientException(new ApiError(0, "this is the description"));
        assertEquals("0: this is the description", exception.getMessage());
    }
}