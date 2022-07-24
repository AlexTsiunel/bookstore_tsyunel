package com.company.server;

public class HttpResponse {
    public enum Status {
        OK(200, "OK"), //
        NOT_FOUND(404, "NOT FOUND"), //
        INTERNAL_ERROR(500, "INTERNAL SERVER ERROR");//

        private final int code;
        private final String message;

        private Status(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    private Status status;
    private String body;

    public HttpResponse(Status status) {
        this.status = status;
    }
//
//    public HttpResponse(Status status, String body) {
//        super();
//        this.status = status;
//        this.body = body;
//    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
