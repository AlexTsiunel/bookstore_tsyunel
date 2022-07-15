package main.java.com.company.service.impl;

import java.util.List;

import main.java.com.company.dao.UserDao;
import main.java.com.company.dao.entity.User;
import main.java.com.company.service.UserService;
import main.java.com.company.service.dto.UserDto;
import main.java.com.company.service.dto.UserDto.Role;

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

    @Override
    public UserDto getUserByEmail(String email) {
        return toDto(userDao.getUserByEmail(email));
    }

    private User toEntity(UserDto dto) {
        User entity = new User();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setRole(toRoleEntity(dto.getRoler()));
        return entity;
    }

    private UserDto toDto(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setRole(toRoleDto(entity.getRole()));

        return dto;
    }

    private User.Role toRoleEntity(UserDto.Role dtoRole) {
        int ordinal = dtoRole.ordinal();
        User.Role[] entityRoles = User.Role.values();
        return entityRoles[ordinal];
    }

    private UserDto.Role toRoleDto(User.Role entityRole) {
        int ordinal = entityRole.ordinal();
        UserDto.Role[] dtoRoles = UserDto.Role.values();
        return dtoRoles[ordinal];
    }
}
