package com.example.sportbookingapplication.Models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Entity
public class User extends BaseModel {

    @NonNull
    private String nameorEmailaddress;


    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;


    private Long Mobile_number;
}
