package com.company.service.impl;

import java.util.List;

import com.company.dao.UserDao;
import com.company.dao.entity.User;
import com.company.service.UserService;
import com.company.service.dto.UserDto;

public class UserServiceImpl implements UserService {
	private final UserDao userDao;

	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDto getById(long id) {
		return toDto(userDao.getById(id));
	}

	@Override
	public List<UserDto> getAll() {
		return userDao.getAll().stream().map(this::toDto).toList();
	}

	@Override
	public UserDto create(UserDto dto) {
		return toDto(userDao.create(toEntity(dto)));
	}

	@Override
	public UserDto update(UserDto dto) {
		return toDto(userDao.update(toEntity(dto)));
	}

	@Override
	public boolean delete(long id) {
		return userDao.delete(id);
	}

	private UserDto toDto(User entity) {
		UserDto dto = new UserDto();
		dto.setId(entity.getId());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		dto.setEmail(entity.getEmail());
		dto.setPassword(entity.getPassword());
		return dto;
	}

	private User toEntity(UserDto dto) {
		User entity = new User();
		entity.setId(dto.getId());
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		return entity;
	}
}
