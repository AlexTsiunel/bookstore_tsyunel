package com.company.app.controller;

import java.util.HashMap;
import java.util.Map;

import com.company.app.service.BookService;
import com.company.app.service.ServiceFactory;
import com.company.app.service.UserService;

public class CommandFactory {
    
    private static class CommandFactoryHolder{
        public static final CommandFactory HOLDER_INSTANCE = new CommandFactory();
    }
    
    private Map<String, Command> commands;

    private CommandFactory() {
        this.commands = new HashMap<>();
        commands.put("books", new BooksCommand(ServiceFactory.getInstance().getService(BookService.class)));
        commands.put("book", new BookCommand(ServiceFactory.getInstance().getService(BookService.class)));
        commands.put("users", new UsersCommand(ServiceFactory.getInstance().getService(UserService.class)));
        commands.put("user", new UserCommand(ServiceFactory.getInstance().getService(UserService.class)));
        commands.put("error", new ErrorCommand());
//        Class<?> clazz = BookService.class;
    }

    public Command getCommand(String command) {
        Command commandInstance = commands.get(command);
        if (commandInstance == null) {
            commandInstance = commands.get("error");
        }
        return commandInstance;
    }

    public static CommandFactory getInstance() {
        return CommandFactoryHolder.HOLDER_INSTANCE;
    }
}
