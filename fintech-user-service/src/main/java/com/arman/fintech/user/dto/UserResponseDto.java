package com.arman.fintech.user.dto;

import com.arman.fintech.user.entity.UserEntity;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UserResponseDto {
//    UserEntity user;
//    UserResponseDto (UserEntity user){
//        this.user=user;
//    }
    private int id;
    private String name;
    private int age;
    private String address;
    private String moreinfo;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
//    private boolean isDelete;

}
