package com.example.sportbookingapplication.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class sport extends BaseModel{
    @Enumerated(EnumType.ORDINAL)
    private SportName sportname;

    @Enumerated(EnumType.ORDINAL)
    private Sportstatus sportstatus;

    private String description;

}
