package com.a00326153.library.service;

import com.a00326153.library.dto.UserDto;
import com.a00326153.library.entity.User;
import com.a00326153.library.mapper.UserMapper;
import com.a00326153.library.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto createUser(User user){
        return UserMapper.mapToUserDto(userRepository.save(user));
    }

    public UserDto getUserById(Long id){
        return UserMapper.mapToUserDto(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User nt found with id: " + id)));
    }

    public Page<UserDto> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(user -> UserMapper.mapToUserDto(user));
    }

    @Transactional
    public UserDto updateUser(Long id, UserDto updatedUserDto) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        existingUser.setName(updatedUserDto.getName());
        existingUser.setEmail(updatedUserDto.getEmail());

        return UserMapper.mapToUserDto(userRepository.save(existingUser));
    }


    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }
}
