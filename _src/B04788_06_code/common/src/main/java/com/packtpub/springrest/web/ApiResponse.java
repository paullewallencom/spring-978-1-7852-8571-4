package com.packtpub.springrest.web;

/**
 * Our envelope.
 *
 * @author Ludovic Dewailly
 */
public class ApiResponse {

    public static enum Status {
        OK,
        ERROR
    }
    public static final class ApiError {

        private final int errorCode;
        private final String description;

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

    private final Status status;
    private final Object data;
    private final ApiError error;

    public ApiResponse(Status status, Object data) {
        this(status, data, null);
    }

    public ApiResponse(Status status, Object data, ApiError error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public Status getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }

    public ApiError getError() {
        return error;
    }
}
