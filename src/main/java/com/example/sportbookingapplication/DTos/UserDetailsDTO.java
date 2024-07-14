package com.example.sportbookingapplication.DTos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserDetailsDTO {

    private String email;
    private String UUID;
    private int amount;
}
