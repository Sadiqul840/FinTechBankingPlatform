package com.arman.fintech.user.service;

import com.arman.fintech.user.dto.UserRequestDto;
import com.arman.fintech.user.dto.UserResponseDto;
import com.arman.fintech.user.entity.UserEntity;
import com.arman.fintech.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.TimeZone;

@Service
public interface UserService{
    public UserResponseDto createUsers(UserRequestDto userRequestDto);
    public UserRequestDto getUsers(int id);
//    UserRequestDto mapToRequestDto(UserEntity user);
//    UserEntity mapToEntity(UserRequestDto userRequestDto);
//    UserResponseDto mapToResponseDto(UserEntity user);
}
