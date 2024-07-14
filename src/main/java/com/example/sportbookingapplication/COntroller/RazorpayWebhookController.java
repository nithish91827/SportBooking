package com.example.sportbookingapplication.COntroller;

import com.example.sportbookingapplication.DTos.UserDetailsDTO;
import com.example.sportbookingapplication.Models.Payment;
import com.example.sportbookingapplication.PaymentService.PaymentService;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.security.MessageDigest;
import java.util.Formatter;

@RestController
@RequestMapping("/api")
public class RazorpayWebhookController {

    @Autowired
    private PaymentService paymentService;  // A service to handle your business logic

        @PostMapping("/payment")
        public Payment handleRazorpayWebhook(@RequestBody String PaymentInfo) {
            // Log the received payload
            System.out.println("Received Razorpay webhook payload: " + PaymentInfo);

            try {
                // Parse the JSON payload
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(PaymentInfo);

                // Extract event details
                String event = jsonNode.get("event").asText();
                JsonNode entity = jsonNode.get("payload").get("payment").get("entity");
               // JsonNode payload= jsonNode.get("payload");

                // Handle payment success or failure based on event type
                    String paymentId = entity.get("id").asText();
                    String status = entity.get("status").asText();
                    long amount = entity.get("amount").asLong();
                  //  String orderId = entity.get("order_id").asText();
                    String Bookingid=entity.get("notes").get("Booking").asText();
                    System.out.println("Payment captured successfully: " + paymentId + ", Amount: " + amount+" "+status);
                    // Additional processing logic here...


                // Respond with success
                return paymentService.savePaymentDetails(paymentId,status,amount,Bookingid);
            } catch (Exception e) {
                // Handle exceptions
                e.printStackTrace();
                return null;
            }
        }
    }

