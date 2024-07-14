package com.example.sportbookingapplication.Payment;

import com.example.sportbookingapplication.DTos.UserDetailsDTO;
import com.example.sportbookingapplication.Payment.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class Razorpay implements Payment {


    public String genertelink(UserDetailsDTO userDetailsDTO) throws RazorpayException {


        RazorpayClient razorpay = new RazorpayClient("rzp_test_46sYTqKKd5NhPs", "WUuWjpktZ9pS4YkmmFof5qPT");
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",userDetailsDTO.getAmount());
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("accept_partial",true);
        paymentLinkRequest.put("first_min_partial_amount",userDetailsDTO.getAmount());
        paymentLinkRequest.put("expire_by",1741023245);
        paymentLinkRequest.put("reference_id","TS"+Double.toString(Math.random()));
        paymentLinkRequest.put("description","Payment for policy no #23456");
       // paymentLinkRequest.put("order_id","TS"+Double.toString(Math.random()));
        JSONObject customer = new JSONObject();
        customer.put("name","+919000090000");
        customer.put("contact","Gaurav Kumar");
        customer.put("email",userDetailsDTO.getEmail());
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);
        JSONObject notes = new JSONObject();

        notes.put("Booking",userDetailsDTO.getUUID());

        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://www.google.com/");
        paymentLinkRequest.put("callback_method","get");
        PaymentLink payment = razorpay.paymentLink.create(paymentLinkRequest);
        return payment.toString();
    }
}
