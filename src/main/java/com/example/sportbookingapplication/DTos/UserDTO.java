package com.example.sportbookingapplication.DTos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String userName;

    private String emailId;

    private String password;

    private String mobileNumber;

    private String roles;

}
