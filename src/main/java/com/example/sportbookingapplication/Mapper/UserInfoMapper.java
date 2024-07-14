package com.example.sportbookingapplication.Mapper;

import com.example.sportbookingapplication.DTos.UserRegistrationDto;
import com.example.sportbookingapplication.Models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInfoMapper {

    private final PasswordEncoder passwordEncoder;
    public User convertToEntity(UserRegistrationDto userRegistrationDto) {
        User userInfoEntity = new User();
        userInfoEntity.setUserName(userRegistrationDto.userName());
        userInfoEntity.setEmailId(userRegistrationDto.userEmail());
        userInfoEntity.setMobileNumber(userRegistrationDto.userMobileNo());
        userInfoEntity.setRoles(userRegistrationDto.userRole());
        userInfoEntity.setPassword(passwordEncoder.encode(userRegistrationDto.userPassword()));
        return userInfoEntity;
    }
}
