package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.UserException;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverterToUser userConverterToUser;


    @Override
    public UserDto saveUser(UserDto userDto) throws UserException {
        validateUserDto(userDto);
        User saveUser = userRepository.save(userConverterToUser.fromUserDtoToUser(userDto));
        return userConverterToUser.fromUserToUserDto(saveUser);
    }

    private void validateUserDto(UserDto userDto) throws UserException {
        if (isNull(userDto)) {
            throw new UserException("is null");
        }
        if (isNull(userDto.getLogin()) || userDto.getLogin().isEmpty()) {
            throw new UserException("is empty");
        }
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserDto findByLogin(String login) {
        User user = userRepository.findByLogin(login);
        if (user != null) {
            return userConverterToUser.fromUserToUserDto(user);
        }
        return null;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userConverterToUser::fromUserToUserDto)
                .collect(Collectors.toList());
    }
}