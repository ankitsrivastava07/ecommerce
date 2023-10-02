package com.users.dto;

import lombok.Data;

@Data
public class ApiResponse {

    private String msg;
    private Boolean status;
    private Object data;
    private Object error;

    public ApiResponse(ApiResponseBuilder apiResponseBuilder) {
        data = apiResponseBuilder.data;
        msg = apiResponseBuilder.msg;
        status = apiResponseBuilder.status;
        error = apiResponseBuilder.error;
    }

    public static class ApiResponseBuilder {
        private String msg;
        private Boolean status;
        private Object data;
        private Object error;

        public ApiResponseBuilder setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public ApiResponseBuilder setStatus(Boolean status) {
            this.status = status;
            return this;
        }

        public ApiResponseBuilder setData(Object data) {
            this.data = data;
            return this;
        }

        public ApiResponseBuilder setError(Object error) {
            this.error = error;
            return this;
        }

        public ApiResponse build() {
            return new ApiResponse(this);
        }
    }

    public static ApiResponseBuilder builder() {
        return new ApiResponseBuilder();
    }
}
