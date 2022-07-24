package com.company.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    private ServerSocket server;
    private static Logger logger = LogManager.getLogger(WebServer.class);

    public void start(int port) {
        try {
            server = new ServerSocket(port);
            logger.info(String.format("Web Server start on %d", port));
            while (true) {
                Socket socket = server.accept();
                new Processor(socket).run();
            }
        } catch (IOException e) {
            logger.error(String.format("Failed to start server on %d", port), e);
        }
    }
}
