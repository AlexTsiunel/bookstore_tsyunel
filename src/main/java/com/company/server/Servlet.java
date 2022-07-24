package com.company.server;

public interface Servlet {
    
    void processRequest(HttpRequest httpRequest, HttpResponse httpResponse);

}
