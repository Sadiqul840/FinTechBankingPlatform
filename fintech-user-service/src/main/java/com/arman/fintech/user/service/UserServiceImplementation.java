package com.arman.fintech.user.service;

import com.arman.fintech.user.dto.UserRequestDto;
import com.arman.fintech.user.dto.UserResponseDto;
import com.arman.fintech.user.entity.UserEntity;
import com.arman.fintech.user.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class UserServiceImplementation implements UserService {
    UserRepository userRepository;
    UserServiceImplementation(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public UserResponseDto createUsers(UserRequestDto userRequestDto){
        UserEntity user=mapToEntity(userRequestDto);
        UserEntity user1=userRepository.save(user);
        return mapToResponseDto(user1);
    }
    public UserRequestDto getUsers(int id){
        return userRepository.findById((long) id)
                .map(this::mapToRequestDto)
                .orElseThrow(() -> new RuntimeException("User not found"));

    }
    private UserRequestDto mapToRequestDto(UserEntity user) {
        UserRequestDto dto = new UserRequestDto();
        dto.setName(user.getName());
        dto.setAge(user.getAge());
        dto.setAddress(user.getAddress());
        dto.setMoreinfo(user.getMoreinfo());
        return dto;
    }
    private UserEntity mapToEntity(UserRequestDto userRequestDto){

        UserEntity user= new UserEntity();
        user.setName(userRequestDto.getName());
        user.setAge(userRequestDto.getAge());
        user.setAddress(userRequestDto.getAddress());
        user.setMoreinfo(userRequestDto.getMoreinfo());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setActive(true);
        user.setDelete(false);


        return user;
    }
    private UserResponseDto mapToResponseDto(UserEntity user){
        UserResponseDto userResponseDto=new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setAge(user.getAge());
        userResponseDto.setAddress(user.getAddress());
        userResponseDto.setMoreinfo(user.getMoreinfo());
        userResponseDto.setActive(user.isActive());
        userResponseDto.setCreatedAt(user.getCreatedAt());
        userResponseDto.setUpdatedAt(user.getUpdatedAt());
        return userResponseDto;
    }
}
