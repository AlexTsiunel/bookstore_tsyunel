package com.company.app.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.company.app.dao.UserDao;
import com.company.app.dao.entity.User;
import com.company.app.service.UserService;
import com.company.app.service.dto.UserDto;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private static Logger logger = LogManager.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDto getById(long id) {
        logger.debug("Method call: UserService.getById.");
        return toDto(userDao.getById(id));
    }

    @Override
    public List<UserDto> getAll() {
        logger.debug("Method call: UserService.getAll.");
        return userDao.getAll().stream().map(this::toDto).toList();
    }

    @Override
    public UserDto create(UserDto dto) {
        logger.debug("Method call: UserService.create.");
        return toDto(userDao.create(toEntity(dto)));
    }

    @Override
    public UserDto update(UserDto dto) {
        logger.debug("Method call: UserService.update.");
        return toDto(userDao.update(toEntity(dto)));
    }

    @Override
    public boolean delete(long id) {
        logger.debug("Method call: UserService.delete.");
        return userDao.delete(id);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        logger.debug("Method call: UserService.getUserByEmail.");
        return toDto(userDao.getUserByEmail(email));
    }

    private User toEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }
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
        if (entity == null) {
            return null;
        }
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
