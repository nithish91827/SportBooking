package com.example.sportbookingapplication.DTos;

import com.example.sportbookingapplication.Models.BookingDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookingDetailsDTO {
    private BookingDetails bookingDetails;
    private String Paymentlink;
}
