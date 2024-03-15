package com.services;
import com.dtos.UserDto;

import java.util.List;

public interface UserService {
    boolean validateLogin(UserDto userDto);
    List<UserDto> getAllUsers();
    UserDto saveUser(UserDto userDto);
    UserDto updateUser(Long id, UserDto userDto);
    UserDto getUserById(Long userId);
    boolean deactivateUser(Long userId);
}
