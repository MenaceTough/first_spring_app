package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.exception.UserException;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Log
@CrossOrigin
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    public UserDto saveUser(@RequestBody UserDto userDto) throws UserException {
        log.info("save user " + userDto);
        return userService.saveUser(userDto);
    }

    @GetMapping("/findAll")
    public List<UserDto> findAllUsers() {
        log.info("find all users");
        return userService.findAll();
    }

    @GetMapping("/findByLogin")
    public UserDto findByLogin(@RequestParam String login) {
        log.info("find by login " + login);
        return userService.findByLogin(login);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Integer id) {
        log.info("delete user " + id);
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}