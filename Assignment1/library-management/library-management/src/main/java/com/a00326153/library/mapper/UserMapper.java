package com.a00326153.library.mapper;

import com.a00326153.library.dto.UserDto;
import com.a00326153.library.entity.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());

        return userDto;
    }
}
