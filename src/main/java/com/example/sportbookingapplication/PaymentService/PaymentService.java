package com.example.sportbookingapplication.PaymentService;

import com.example.sportbookingapplication.DTos.UserDetailsDTO;
import com.example.sportbookingapplication.Models.BookingDetails;
import com.example.sportbookingapplication.Models.BookingStatus;
import com.example.sportbookingapplication.Models.Payment;
import com.example.sportbookingapplication.Payment.BestAvailablePaymentMode;
import com.example.sportbookingapplication.Repository.Bookingrepositorydetails;
import com.example.sportbookingapplication.Repository.PaymentRepository;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {


    @Autowired
    private BestAvailablePaymentMode bestAvailablePaymentMode;
    @Autowired
    private PaymentRepository paymentRepository;
@Autowired
private Bookingrepositorydetails bookingrepositorydetails;

    public String generatepaymentlink(UserDetailsDTO userDetailsDTO) throws RazorpayException {

        return bestAvailablePaymentMode.getmode().genertelink(userDetailsDTO);
    }
    public Payment savePaymentDetails(String paymentId,String status, Long amount,String Bookingid) {
        Payment payment = new Payment();
        payment.setPaymentId(paymentId);
        payment.setStatus(status);
        payment.setAmount(amount);
        Optional<BookingDetails> details=bookingrepositorydetails.findById(UUID.fromString(Bookingid));
        details.get().setStatus(status!="failed"? BookingStatus.Success:BookingStatus.Failure);
        bookingrepositorydetails.save(details.get());
        payment.setBookingid(details.get());
        // Set other fields
      return  paymentRepository.save(payment);
    }

    public void updatePaymentStatus(String paymentId, String status) {
        Payment payment = paymentRepository.findByPaymentId(paymentId);
        if (payment != null) {
            payment.setStatus(status);
            paymentRepository.save(payment);
        }
    }
}

