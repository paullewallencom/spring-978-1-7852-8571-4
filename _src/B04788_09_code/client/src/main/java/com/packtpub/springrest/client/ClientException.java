package com.packtpub.springrest.client;

import com.packtpub.springrest.client.internal.ApiResponse.ApiError;

/**
 * A specialised {@link RuntimeException}.
 *
 * @author Ludovic Dewailly
 */
public class ClientException extends RuntimeException {

    private static final long serialVersionUID = 2789017069143115323L;

    private final int errorCode;
    private final String errorDescription;

    public ClientException(ApiError error) {
        super(error.getErrorCode() + ": " + error.getDescription());
        this.errorCode = error.getErrorCode();
        this.errorDescription = error.getDescription();
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
