package com.example.sportbookingapplication.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Location extends BaseModel{
    @NonNull
   private City city;
    private double latitude;
    private double longitude;
    @Column(name = "Location_Address",nullable = false)
    private String Address;
}
