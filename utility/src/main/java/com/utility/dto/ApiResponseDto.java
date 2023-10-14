package com.utility.dto;

public class ApiResponseDto {

    private String msg;
    private Object data;
    private Boolean status;

    public ApiResponseDto(APiResponseBuilder aPiResponseBuilder) {
        this.msg = aPiResponseBuilder.msg;
        this.data = aPiResponseBuilder.data;
        this.status = aPiResponseBuilder.status;
    }

    public String getMsg() {
        return msg;
    }


    public Object getData() {
        return data;
    }


    public Boolean getStatus() {
        return status;
    }


    public static class APiResponseBuilder {

        private String msg;
        private Object data;
        private Boolean status;

        public APiResponseBuilder setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public APiResponseBuilder setData(Object data) {
            this.data = data;
            return this;
        }

        public APiResponseBuilder setStatus(Boolean status) {
            this.status = status;
            return this;
        }

        public ApiResponseDto build() {
            return new ApiResponseDto(this);
        }
    }

    public static APiResponseBuilder builder() {
        return new APiResponseBuilder();
    }
}
