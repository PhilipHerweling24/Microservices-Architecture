package com.a00326153.library.service;

import com.a00326153.library.dto.UserDto;
import com.a00326153.library.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDto createUser(User user);

    UserDto getUserById(Long id);

    Page<UserDto> getAllUsers(Pageable pageable);

    UserDto updateUser(Long id, UserDto updatedUserDto);

    void deleteUser(Long id);
}
