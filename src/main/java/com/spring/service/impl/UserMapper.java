package com.spring.service.impl;

import com.spring.dto.UserResponseDto;
import com.spring.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDto replaceOnDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setName(user.getName());
        userResponseDto.setId(user.getId());
        return userResponseDto;
    }
}
