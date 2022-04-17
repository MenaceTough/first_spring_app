package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.exception.UserException;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto userDto) throws UserException;

    void deleteUser(Integer userId);

    UserDto findByLogin(String login);

    List<UserDto> findAll();
}