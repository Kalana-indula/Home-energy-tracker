package com.energy.user_service.service;

import com.energy.user_service.dto.UserDto;
import com.energy.user_service.dto.response.SingleEntityResponse;
import com.energy.user_service.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    SingleEntityResponse<UserDto> createUser(User user);

    SingleEntityResponse<UserDto> getUserById(Long id);

    SingleEntityResponse<String> deleteUser(Long id);
}
