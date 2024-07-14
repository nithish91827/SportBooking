package com.example.sportbookingapplication.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Payment extends BaseModel{

    private String paymentId;
    private String status;
    private Long amount;
    private String email;
    @ManyToOne
    private BookingDetails bookingid;
}

