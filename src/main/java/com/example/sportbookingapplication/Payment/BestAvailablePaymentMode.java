package com.example.sportbookingapplication.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BestAvailablePaymentMode {
    @Autowired
    public Razorpay razorpay;

    public Payment getmode(){

        return razorpay;

    }
}
