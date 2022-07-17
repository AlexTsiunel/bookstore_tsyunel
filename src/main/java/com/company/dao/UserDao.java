package com.company.dao;

import java.util.List;

import com.company.dao.entity.User;

public interface UserDao {
	User getById(long id);

	List<User> getAll();

	User create(User user);

	User update(User user);

	boolean delete(long id);

	User getUserByEmail(String email);
}
