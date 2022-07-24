package com.company.app.service;

import java.util.List;

import com.company.app.service.dto.UserDto;



public interface UserService {
	UserDto getById(long id);

	List<UserDto> getAll();

	UserDto create(UserDto userDto);

	UserDto update(UserDto userDto);

	boolean delete(long id);
	
	UserDto getUserByEmail(String email);
}
