package com.example.sportbookingapplication.Payment;

import com.razorpay.RazorpayException;

public interface Payment {
    public String genertelink(int amount) throws RazorpayException;
}
