package com.example.demo.dto;

import com.example.demo.entity.User;

public class UserDtoTest {

    public static User userTest() {
        User u = new User();
        u.setName("test_name");
        u.setLogin("test_login");
        return u;
    }

    public static UserDto userDtoTest() {
        return UserDto.builder()
                .name("test_name")
                .login("test_login")
                .build();
    }
}
