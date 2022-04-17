package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.exception.UserException;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.demo.dto.UserDtoTest.userDtoTest;
import static com.example.demo.dto.UserDtoTest.userTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

        private UserRepository userRepository;
        private UserConverterToUser userConverterToUser;
        private UserService userService;

        @BeforeEach
        void setUp() {
            userRepository = mock(UserRepository.class);
            userConverterToUser = new UserConverterToUser();
            userService = new UserServiceImpl(userRepository, userConverterToUser);
        }

        @Test
        void saveUser() throws UserException {
            when(userRepository.save(any())).thenReturn(userTest());
            UserDto createdUser = userService.saveUser(userDtoTest());
            assertThat(createdUser).isNotNull();
            assertThat(createdUser.getName()).isEqualTo(userDtoTest().getName());
        }

        @Test
        void saveUserThrowsValidationExceptionWhenLoginIsNull() {
            UserDto userDto = userDtoTest();
            userDto.setLogin("");
            assertThrows(UserException.class,
                    () -> userService.saveUser(userDto),
                    "no login");
        }

        @Test
        void deleteUser() {
        }

        @Test
        void findByLogin() {
            when(userRepository.findByLogin(eq("test_login"))).thenReturn(userTest());
            UserDto foundUser = userService.findByLogin("test_login");
            assertThat(foundUser).isNotNull();
            assertThat(foundUser.getLogin()).isEqualTo("test_login");
        }

        @Test
        void findAll() {
        }
    }

