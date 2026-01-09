package com.energy.user_service.service.impl;

import com.energy.user_service.dto.UserDto;
import com.energy.user_service.dto.response.SingleEntityResponse;
import com.energy.user_service.entity.User;
import com.energy.user_service.repository.UserRepository;
import com.energy.user_service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //create a new user
    @Override
    public SingleEntityResponse<UserDto> createUser(User user) {

        //new response
        SingleEntityResponse<UserDto> response = new SingleEntityResponse<>();

        User createdUser = userRepository.save(user);

        response.setMessage("user created successfully");
        response.setData(toDto(createdUser));

        return response;
    }

    @Override
    public SingleEntityResponse<UserDto> getUserById(Long id) {

        //new response
        SingleEntityResponse<UserDto> response = new SingleEntityResponse<>();

        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser == null) {
            response.setMessage("user not found");
            response.setData(null);

            return response;
        } else {
            response.setMessage("user found");
            response.setData(toDto(existingUser));

            return response;
        }
    }

    @Override
    public SingleEntityResponse<String> deleteUser(Long id) {

        SingleEntityResponse<String> response = new SingleEntityResponse<>();

        boolean isExist = userRepository.existsById(id);

        if (!isExist) {
            response.setMessage("user not found");
            response.setData(null);

            return response;
        }

        userRepository.deleteById(id);

        response.setMessage("user deleted successfully");
        response.setData(null);

        return response;

    }

    private UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .address(user.getAddress())
                .alerting(user.isAlerting())
                .energyAlertingThreshold(user.getEnergyAlertingThreshold())
                .build();
    }

}
