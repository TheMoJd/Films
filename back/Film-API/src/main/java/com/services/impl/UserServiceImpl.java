package com.services.impl;

import com.services.UserService;
import com.entities.User;
import com.dtos.UserDto;
import com.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean validateLogin(UserDto userDto) {
        return userRepository.findByUsername(userDto.getUsername())
                .map(user -> user.getPassword().equals(userDto.getPassword()))
                .orElse(false);
    }

    public UserDto saveUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setAdmin(userDto.getIs_admin());
        user.setActive(userDto.getActive());
        user = userRepository.save(user);
        return convertToDto(user);
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        return userRepository.findById(id).map(user -> {
            // Mettre à jour uniquement les champs spécifiés
            if(userDto.getUsername() != null) user.setUsername(userDto.getUsername());
            // N'incluez pas le mot de passe ici pour éviter de l'écraser
            if(userDto.getActive() != null) user.setActive(userDto.getActive());
            if(userDto.getIs_admin() != null) user.setAdmin(userDto.getIs_admin());

            // Sauvegarder les modifications
            User updatedUser = userRepository.save(user);
            return convertToDto(updatedUser);
        }).orElse(null); // ou lancez une exception si l'utilisateur n'est pas trouvé
    }



    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(this::convertToDto)
                .orElse(null);
    }

    public boolean deactivateUser(Long userId) {
        return userRepository.findById(userId).map(user -> {
            user.setActive(false);
            userRepository.save(user);
            return true;
        }).orElse(false);
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setActive(user.isActive());
        userDto.setIs_admin(user.isAdmin());
        return userDto;
    }
}
