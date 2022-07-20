package com.company.simpleserver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Processor {
    private BufferedReader br;
    private PrintWriter out;
    private static Logger logger = LogManager.getLogger(Processor.class);

    public Processor(Socket socket) {
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            logger.info("Opened I/O streams\n");
        } catch (IOException e) {
            logger.error("Failed to open I/O streams\n", e);
        }
    }

    public void run() {
        System.out.println(parse(br));
    }

    //TEST METHOD
    private String parse(BufferedReader br) {
        String inputContent = null;
        try {
            inputContent = inputContent = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputContent;
    }


    private HttpRequest processRequest(BufferedReader br) throws IOException {

        String inputContent = br.readLine();
        if (inputContent == null || inputContent.length() == 0) {
            sendError(400, "Client invoke error");
            return null;
        }

        String request[] = inputContent.split(" ");
        if (request.length != 3) {
            sendError(400, "Client invoke error");
            return null;
        }
        String url = request[1];
        if (isUrlNotValid(url)) {
            sendError(400, "Client invoke error");
            return null;
        }
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setTypeRequest(HttpRequest.TypeRequest.valueOf(request[0]));
        httpRequest.setHttpVersion(request[2]);

        //   /books
        //   /book?id=15
        //   /book?title=Java&author=Schildt

        String[] urlElements = parseURL(url);
        httpRequest.setURL(urlElements[0].substring(1));
        if (urlElements.length > 1) {
            List<String> parameterList = new ArrayList<>();
            for (int i = 1; i < urlElements.length; i++) {
                parameterList.add(urlElements[i]);
            }
        }
        return httpRequest;
    }


    private boolean isUrlNotValid(String url) {
        return !url.matches("((/books)|(/users))|((book)|(user)?id=[1-9][0-9]*)");
    }

    private String[] parseURL(String url) {
        return url.split("[?|&]");
    }


    private void sendError(int errNum, String errMsg) {
        out.println("HTTP/1.0" + errNum + " " + errMsg);
        out.println("Content-type: text/html");
        out.println();
        out.println("<html>");
        out.println("<head><title> " + errNum + "--" + errMsg + "</tile></head>");
        out.println("<h1>" + errNum + " " + errMsg + "</h1");
        out.println("</html>");
        out.println();
        out.flush();
        out.close();
    }
}
