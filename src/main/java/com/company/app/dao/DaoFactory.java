package com.company.app.dao;

import java.util.HashMap;
import java.util.Map;

import com.company.app.dao.connection.DataSource;
import com.company.app.dao.impl.BookDaoImpl;
import com.company.app.dao.impl.UserDaoImpl;

public class DaoFactory {
    private final Map<Class<?>, Object> map;

    private static class DaoFactotyHolder {
        public static final DaoFactory HOLDER_INSTANCE = new DaoFactory();
    }

    private DaoFactory() {
        map = new HashMap<>();
        map.put(BookDao.class, new BookDaoImpl(DataSource.getInstance()));
        map.put(UserDao.class, new UserDaoImpl(DataSource.getInstance()));
    }

    @SuppressWarnings("unchecked")
    public <T> T getDao(Class<T> clazz) {
        return (T) map.get(clazz);
    }

    public static DaoFactory getInstance() {
        return DaoFactotyHolder.HOLDER_INSTANCE;
    }
}
