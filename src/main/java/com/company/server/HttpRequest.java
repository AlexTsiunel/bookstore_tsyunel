package com.company.server;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    TypeRequest typeRequest;
    private String URL;
    private Map<String, String> parameters;
    private String httpVersion;

    enum TypeRequest {
        GET, POST, PUT, DELETE
    }
    

    public HttpRequest() {
        super();
        this.parameters = new HashMap<>();
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

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }
}
