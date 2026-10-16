package com.arman.fintech.user.service;

import com.arman.fintech.user.dto.UserRequestDto;
import com.arman.fintech.user.dto.UserResponseDto;
import com.arman.fintech.user.entity.UserEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class UserServiceLogging implements UserService{
    UserServiceImplementation userServiceImplementation;
    public UserServiceLogging(UserServiceImplementation userServiceImplementation){
        this.userServiceImplementation=userServiceImplementation;
    }


    @Override
    public UserResponseDto createUsers(UserRequestDto userRequestDto) {
        long start=System.currentTimeMillis();
        System.out.println("create User Started");
        UserResponseDto userResponseDto=userServiceImplementation.createUsers(userRequestDto);
        long end=System.currentTimeMillis();
        long timeTaken=end-start;
        System.out.println("total time taken : "+ timeTaken);
        System.out.println("create User ended");
        return userResponseDto;
    }

    @Override
    public UserRequestDto getUsers(int id) {

        long start=System.currentTimeMillis();
        System.out.println("get User Started");
        UserRequestDto userRequestDto=userServiceImplementation.getUsers(id);
        long end=System.currentTimeMillis();
        long timeTaken=end-start;
        System.out.println("total time taken : "+ timeTaken);
        System.out.println("get User ended");
        return userRequestDto;
    }
//    @Override
//    public UserRequestDto mapToRequestDto(UserEntity user) {
//        long start=System.currentTimeMillis();
//        System.out.println("mapToRequestDto Started");
//        UserRequestDto userRequestDto=userServiceImplementation.mapToRequestDto(user);
//        long end=System.currentTimeMillis();
//        long timeTaken=end-start;
//        System.out.println("total time taken : "+ timeTaken);
//        System.out.println("mapToRequestDto ended");
//        return userRequestDto;
//    }
//    @Override
//    public UserEntity mapToEntity(UserRequestDto userRequestDto) {
//        long start=System.currentTimeMillis();
//        System.out.println("mapToEntity Started");
//        UserEntity userEntity=userServiceImplementation.mapToEntity(userRequestDto);
//        long end=System.currentTimeMillis();
//        long timeTaken=end-start;
//        System.out.println("total time taken : "+ timeTaken);
//        System.out.println("mapToEntity ended");
//        return userEntity;
//    }
//
//    @Override
//    public UserResponseDto mapToResponseDto(UserEntity user){
//        long start=System.currentTimeMillis();
//        System.out.println("mapToResponseDto Started");
//        UserResponseDto userResponseDto=userServiceImplementation.mapToResponseDto(user);
//        long end=System.currentTimeMillis();
//        long timeTaken=end-start;
//        System.out.println("total time taken : "+ timeTaken);
//        System.out.println("mapToResponseDto");
//        return userResponseDto;
//    }

}
