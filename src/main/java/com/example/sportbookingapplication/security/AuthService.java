package com.example.sportbookingapplication.security;

import com.example.sportbookingapplication.DTos.AuthResponseDto;
import com.example.sportbookingapplication.DTos.TokenType;
import com.example.sportbookingapplication.DTos.UserRegistrationDto;
import com.example.sportbookingapplication.Mapper.UserInfoMapper;
import com.example.sportbookingapplication.Models.User;
import com.example.sportbookingapplication.Repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserInfoMapper userInfoMapper;
    private final UserRepository userInfoRepo;
    private final JwtTokenGenerator jwtTokenGenerator;
    public AuthResponseDto getJwtTokensAfterAuthentication(Authentication authentication) {
        try
        {
            var userInfoEntity = userInfoRepo.findByEmailId(authentication.getName())
                    .orElseThrow(()->{
                        log.error("[AuthService:userSignInAuth] User :{} not found",authentication.getName());
                        return new ResponseStatusException(HttpStatus.NOT_FOUND,"USER NOT FOUND ");});


            String accessToken = jwtTokenGenerator.generateAccessToken(authentication);

            log.info("[AuthService:userSignInAuth] Access token for user:{}, has been generated",userInfoEntity.getUserName());
            return  AuthResponseDto.builder()
                    .accessToken(accessToken)
                    .accessTokenExpiry(15 * 60)
                    .userName(userInfoEntity.getUserName())
                    .tokenType(TokenType.Bearer)
                    .build();


        }catch (Exception e){
            log.error("[AuthService:userSignInAuth]Exception while authenticating the user due to :"+e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Please Try Again");
        }
    }

    public AuthResponseDto registerUser(UserRegistrationDto userRegistrationDto, HttpServletResponse httpServletResponse){

        try{
            log.info("[AuthService:registerUser]User Registration Started with :::{}",userRegistrationDto);

            Optional<User> user = userInfoRepo.findByEmailId(userRegistrationDto.userEmail());
            if(user.isPresent()){
                throw new Exception("User Already Exist");
            }

            User userDetailsEntity = userInfoMapper.convertToEntity(userRegistrationDto);
            Authentication authentication = createAuthenticationObject(userDetailsEntity);


            // Generate a JWT token
            String accessToken = jwtTokenGenerator.generateAccessToken(authentication);
            //String refreshToken = jwtTokenGenerator.generateRefreshToken(authentication);

            User savedUserDetails = userInfoRepo.save(userDetailsEntity);
            // saveUserRefreshToken(userDetailsEntity,refreshToken);

            //   creatRefreshTokenCookie(httpServletResponse,refreshToken);

            log.info("[AuthService:registerUser] User:{} Successfully registered",savedUserDetails.getUserName());
            return   AuthResponseDto.builder()
                    .accessToken(accessToken)
                    .accessTokenExpiry(5 * 60)
                    .userName(savedUserDetails.getUserName())
                    .tokenType(TokenType.Bearer)
                    .build();


        }catch (Exception e){
            log.error("[AuthService:registerUser]Exception while registering the user due to :"+e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }

    }

    private static Authentication createAuthenticationObject(User userInfoEntity) {
        // Extract user details from UserDetailsEntity
        String username = userInfoEntity.getEmailId();
        String password = userInfoEntity.getPassword();
        String roles = userInfoEntity.getRoles();

        // Extract authorities from roles (comma-separated)
        String[] roleArray = roles.split(",");
        GrantedAuthority[] authorities = Arrays.stream(roleArray)
                .map(role -> (GrantedAuthority) role::trim)
                .toArray(GrantedAuthority[]::new);

        return new UsernamePasswordAuthenticationToken(username, password, Arrays.asList(authorities));
    }
}

