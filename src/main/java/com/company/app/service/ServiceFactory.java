package com.company.app.service;

import java.util.HashMap;
import java.util.Map;

import com.company.app.dao.BookDao;
import com.company.app.dao.DaoFactory;
import com.company.app.dao.UserDao;
import com.company.app.service.impl.BookServiceImpl;
import com.company.app.service.impl.UserServiceImpl;

public class ServiceFactory {
    private final Map<Class<?>, Object> map;

    private static class ServiceFactoryHolder {
        public static final ServiceFactory HOLDER_INSTANCE = new ServiceFactory();
    }

    private ServiceFactory() {
        map = new HashMap<>();
        map.put(BookService.class, new BookServiceImpl(DaoFactory.getInstance().getDao(BookDao.class)));
        map.put(UserService.class, new UserServiceImpl(DaoFactory.getInstance().getDao(UserDao.class)));
    }

    @SuppressWarnings("unchecked")
    public <T> T getService(Class<T> clazz) {
        return (T) map.get(clazz);
    }

    public static ServiceFactory getInstance() {
        return ServiceFactoryHolder.HOLDER_INSTANCE;
    }
}
