package com.example.sportbookingapplication.Bookingservice;


//import com.example.sportbookingapplication.Models.BookingDetails;
//import com.example.sportbookingapplication.Models.BookingStatus;
//import com.example.sportbookingapplication.Repository.Bookingrepositorydetails;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.Duration;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//@Service
//@RequiredArgsConstructor
//public class UpdateNonbookedBookingDetails {
//    private Bookingrepositorydetails bookingrepositorydetails;
//    void CheckandUpdate(){
//        List<BookingDetails> PendingDetails= bookingrepositorydetails.findAllByStatus_Pending();
//        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
//
//        // Parse the created time from the DTO
//
//        for(BookingDetails details:PendingDetails) {
//            LocalDateTime createdTime = LocalDateTime.parse((CharSequence) details.getCreatedAt(), format);
//
//            if (details.getStatus().equals(BookingStatus.Pending) &&
//                    Math.abs(Duration.between(LocalDateTime.now(), createdTime).toMinutes())>5)
//                    details.setStatus(BookingStatus.NoProgress);
//        }
//    }
//}
