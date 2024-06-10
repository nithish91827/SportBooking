package com.example.sportbookingapplication.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
public class BookingDetails extends BaseModel{
    @ManyToOne
//@Cascade(value = CascadeType.ALL)
      private sportVenue sportVenue;

@ManyToOne
//@Cascade(value = CascadeType.ALL)
      private User user;

@NonNull
    @Enumerated(EnumType.ORDINAL)
      private BookingStatus Status;

   @NonNull
    @Enumerated(EnumType.ORDINAL)
private City city;

    private LocalDateTime starttime;

    private LocalDateTime endTime;


    int amount;


}
