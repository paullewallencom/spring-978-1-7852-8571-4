package com.packtpub.springrest.client.internal;

/**
 * An api response we expect from the server.
 *
 * @author Ludovic Dewailly
 */
public class ApiResponse<T> {

    public static enum Status {
        OK,
        ERROR
    }
    public static final class ApiError {

        private int errorCode;
        private String description;

        private ApiError() { }

        public ApiError(int errorCode, String description) {
            this.errorCode = errorCode;
            this.description = description;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public String getDescription() {
            return description;
        }
    }

    private Status status;
    private T data;
    private ApiError error;

    private ApiResponse() { }

    public ApiResponse(Status status, T data, ApiError error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public Status getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public ApiError getError() {
        return error;
    }
}
