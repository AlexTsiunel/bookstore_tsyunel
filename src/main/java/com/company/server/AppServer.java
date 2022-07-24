package com.company.server;

public class AppServer {
    public static void main(String[] args) {
        WebServer webServer = new WebServer();
        webServer.start(8081);
    }
}
