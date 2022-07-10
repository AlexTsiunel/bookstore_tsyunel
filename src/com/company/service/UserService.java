package com.company.service;

import java.util.List;

import com.company.service.dto.UserDto;

public interface UserService {
	UserDto getById(long id);

	List<UserDto> getAll();

	UserDto create(UserDto userDto);

	UserDto update(UserDto userDto);

	boolean delete(long id);
}
