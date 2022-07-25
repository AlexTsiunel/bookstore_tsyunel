package com.company.app.controller;

//import java.util.List;
//import java.util.Map;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import com.company.app.service.UserService;
//import com.company.app.service.dto.UserDto;
//import com.company.server.HttpRequest;
//import com.company.server.HttpResponse;
//import com.company.server.Servlet;

import jakarta.servlet.http.HttpServlet;

//public class UserSimpleController implements Servlet {
//    private final UserService userService;
//    private static Logger logger = LogManager.getLogger(UserSimpleController.class);
//
//    public UserSimpleController(UserService userService) {
//        super();
//        this.userService = userService;
//    }
//
//    @Override
//    public void processRequest(HttpRequest httpRequest, HttpResponse httpResponse) {
//        switch (httpRequest.getURL()) {
//        case "users": {
//            List<UserDto> users = userService.getAll();
//            StringBuilder response = new StringBuilder("""
//                    <html>
//                        <head>
//                            <title>Users</title>
//                        </head>
//                        <body>
//                            <table>
//                                <th>Id</th><th>First name</th><th>Last name</th>
//                    """);
//            for (UserDto user : users) {
//                response.append("<tr><td>")//
//                        .append(user.getId())//
//                        .append("</td><td>")//
//                        .append(user.getFirstName())//
//                        .append("</td><td>")//
//                        .append(user.getLastName())//
//                        .append("</td></tr>");
//            }
//            response.append("</table></body></html>");
//            httpResponse.setBody(response.toString());
//            logger.info("Response generated for URL: users");
//            break;
//        }
//
//        case "user": {
//            Map<String, String> parameters = httpRequest.getParameters();
//            if (parameters.containsKey("id")) {
//                UserDto user = userService.getById(Integer.parseInt(parameters.get("id")));
//                if (user == null) {
//                    StringBuilder response = new StringBuilder("""
//                            <html>
//                                <head>
//                                    <title>User</title>
//                                </head>
//                                <body>
//                                User not found!!!
//                                </body>
//                             </html>
//                            """);
//                    httpResponse.setBody(response.toString());
//                    logger.info("Response generated for URL: user");
//                    break;
//                }
//                StringBuilder response = new StringBuilder("""
//                        <html>
//                            <head>
//                                <title>User</title>
//                            </head>
//                            <body>
//                                <table>
//                                    <th>Id</th><th>Title</th>
//                        """);
//                response.append("<tr><td>")//
//                        .append(user.getId())//
//                        .append("</td><td>")//
//                        .append(user.getFirstName())//
//                        .append("</td><td>")//
//                        .append(user.getLastName())//
//                        .append("</td></tr>");
//                response.append("</table></body></html>");
//                httpResponse.setBody(response.toString());
//                logger.info("Response generated for URL: user");
//            }
//            break;
//        }
//        }
//    }
//}

public class UserSimpleController extends HttpServlet{
    
}
