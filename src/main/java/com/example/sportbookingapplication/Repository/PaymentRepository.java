package com.example.sportbookingapplication.Repository;

import com.example.sportbookingapplication.Models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    Payment findByPaymentId(String paymentId);
}
