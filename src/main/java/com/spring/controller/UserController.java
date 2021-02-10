package com.spring.controller;

import com.spring.dto.UserResponseDto;
import com.spring.model.User;
import com.spring.service.UserService;
import com.spring.service.impl.UserMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @ResponseBody
    @GetMapping("/inject")
    public void createUsers() {
        User user = new User();
        user.setName("Bob");
        user.setEmail("@bob.ua");
        userService.add(user);
        User userAlice = new User();
        userAlice.setName("Alice");
        userAlice.setEmail("@ali.ua");
        userService.add(userAlice);
        User userMax = new User();
        userMax.setName("Max");
        userMax.setEmail("@max.ua");
        userService.add(userMax);
        User userJohn = new User();
        userJohn.setName("John");
        userJohn.setEmail("@john.ua");
        userService.add(userJohn);
    }

    @ResponseBody
    @GetMapping("/{userId}")
    public UserResponseDto get(@PathVariable(value = "userId") Long id) {
        User user = userService.get(id);
        UserResponseDto userResponseDto = userMapper.replaceOnDto(user);
        return userResponseDto;
    }

    @ResponseBody
    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.listUsers().stream()
                .map(user -> userMapper.replaceOnDto(user))
                .collect(Collectors.toList());
    }
}
