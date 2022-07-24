package com.company.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.company.app.controller.BookSimpleController;
import com.company.app.controller.UserSimpleController;
import com.company.app.dao.connection.DataSource;
import com.company.app.dao.impl.BookDaoImpl;
import com.company.app.dao.impl.UserDaoImpl;
import com.company.app.service.impl.BookServiceImpl;
import com.company.app.service.impl.UserServiceImpl;

import java.io.*;
import java.net.Socket;

public class Processor {
    private BufferedReader br;
    private PrintWriter out;
    private static Logger logger = LogManager.getLogger(Processor.class);

    public Processor(Socket socket) {
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            logger.info("Opened I/O streams");
        } catch (IOException e) {
            logger.error("Failed to open I/O streams", e);
        }
    }

    public void run() {
        HttpRequest httpRequest = processRequest(br);
        if (httpRequest != null) {

            HttpResponse httpResponse = new HttpResponse(HttpResponse.Status.OK);
            if (httpRequest.getURL().matches("(users)|(user)")) {
                new UserSimpleController(new UserServiceImpl(new UserDaoImpl(new DataSource())))
                        .processRequest(httpRequest, httpResponse);
            } else if (httpRequest.getURL().matches("(books)|(book)")) {
                new BookSimpleController(new BookServiceImpl(new BookDaoImpl(new DataSource())))
                        .processRequest(httpRequest, httpResponse);
            }
            sendResponse(httpResponse);
            logger.info("Response sent to client");

        }
    }

    private HttpRequest processRequest(BufferedReader br) {

        String inputContent = null;
        try {
            inputContent = br.readLine();
            logger.info("Read content.");
        } catch (IOException e) {
            logger.error("Failed to read line.", e);

        }
        if (inputContent == null || inputContent.length() == 0) {
            sendError(400, "Client invoke error");
            return null;
        }

        String[] requestElements = inputContent.split(" ");
        if (requestElements.length != 3) {
            sendError(400, "Client invoke error");
            return null;
        }
        String url = requestElements[1];
        if (isUrlNotValid(url)) {
            sendError(400, "Client invoke error");
            return null;
        }
        HttpRequest httpRequest = new HttpRequest();

        httpRequest.setTypeRequest(HttpRequest.TypeRequest.valueOf(requestElements[0]));
        parseURL(httpRequest, requestElements[1]);
        httpRequest.setHttpVersion(requestElements[2]);

        return httpRequest;
    }

    private boolean isUrlNotValid(String url) {
        return !url.matches("((\\/books)|(\\/users))|(((\\/book)|(\\/user))[?]id=[1-9][0-9]*)");
    }

    private void parseURL(HttpRequest httpRequest, String url) {
        String[] urlElements = url.split("[?]");
        httpRequest.setURL(urlElements[0].substring(1));
        if (urlElements.length > 1) {
            String[] urlParametrs = urlElements[1].split("[&]");
            for (String parametr : urlParametrs) {
                String[] elements = parametr.split("[=]");
                httpRequest.getParameters().put(elements[0], elements[1]);
            }
        }
    }

    private void sendError(int errNum, String errMsg) {
        out.println("HTTP/1.1" + errNum + " " + errMsg);
        out.println("Content-type: text/html");
        out.println();
        out.println("<html>");
        out.println("<head><title>Error " + errNum + "--" + errMsg + "</title></head>");
        out.println("<h1>" + errNum + " " + errMsg + "</h1>");
        out.println("</html>");
        out.println();
        out.flush();
        out.close();
    }

    private void sendResponse(HttpResponse response) {
        HttpResponse.Status status = response.getStatus();
        out.println("HTTP/1.1 " + status.getCode() + " " + status.getCode());
        out.println();
        out.println(response.getBody());
        out.println();
        out.flush();
        out.close();
    }
}
