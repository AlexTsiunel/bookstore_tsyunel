package com.company.simpleserver;

import java.util.List;
import java.util.Objects;

public class HttpRequest {
    TypeRequest typeRequest;
    private String URL;
    private List<String> parameters;
    private String httpVersion;



    enum TypeRequest {
        GET,
        POST,
        PUT,
        DELETE
    }

    public TypeRequest getTypeRequest() {
        return typeRequest;
    }

    public void setTypeRequest(TypeRequest typeRequest) {
        this.typeRequest = typeRequest;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }
    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

}
