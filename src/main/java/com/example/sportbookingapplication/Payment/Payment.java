package com.example.sportbookingapplication.Payment;

import com.example.sportbookingapplication.DTos.UserDetailsDTO;
import com.razorpay.RazorpayException;

public interface Payment {
    public String genertelink(UserDetailsDTO userDetailsDTO) throws RazorpayException;
}
