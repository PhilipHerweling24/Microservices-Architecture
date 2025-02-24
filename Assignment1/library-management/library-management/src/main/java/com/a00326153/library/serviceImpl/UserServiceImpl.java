package com.a00326153.library.serviceImpl;

import com.a00326153.library.constants.ServiceConstants;
import com.a00326153.library.dto.UserDto;
import com.a00326153.library.entity.User;
import com.a00326153.library.exception.EntityAlreadyExistsException;
import com.a00326153.library.exception.ResourceNotFoundException;
import com.a00326153.library.mapper.UserMapper;
import com.a00326153.library.repository.UserRepository;
import com.a00326153.library.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    //Create a User
    public UserDto createUser(User user){
        if (userRepository.existsByEmail(user.getEmail())){
            throw new EntityAlreadyExistsException("USer with Email "+ user.getEmail()+ " Already exists");
        }
        return UserMapper.mapToUserDto(userRepository.save(user));
    }

    //Get user by there User ID
    public UserDto getUserById(Long id){
        return UserMapper.mapToUserDto(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id)));
    }

    //Get all users
    public Page<UserDto> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(user -> UserMapper.mapToUserDto(user));

    }

    //Update a user using there ID and passing in the user object
    @Transactional
    public UserDto updateUser(Long id, UserDto updatedUserDto) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ServiceConstants.STATUS_417+ ServiceConstants.MESSAGE_417_UPDATE));
        existingUser.setName(updatedUserDto.getName());
        existingUser.setEmail(updatedUserDto.getEmail());

        return UserMapper.mapToUserDto(userRepository.save(existingUser));
    }


    //Delete a user by using there user ID
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(ServiceConstants.STATUS_417+ ServiceConstants.MESSAGE_417_DELETE);
        }
        userRepository.deleteById(id);
    }
}
