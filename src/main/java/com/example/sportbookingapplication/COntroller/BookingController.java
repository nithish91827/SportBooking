package com.example.sportbookingapplication.COntroller;

import com.example.sportbookingapplication.Bookingservice.BookingService;
import com.example.sportbookingapplication.DTos.BookingDetailsDTO;
import com.example.sportbookingapplication.DTos.UserpreferedenueDetailsDTO;
import com.example.sportbookingapplication.Models.*;
import com.example.sportbookingapplication.PaymentService.PaymentService;
import com.example.sportbookingapplication.Repository.BookngReposiryforsportsvenue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private PaymentService paymentService;

    @PreAuthorize("hasAuthority('SCOPE_READ')")
    @GetMapping("{id}/{cityid}")
    public List<sportVenue> get( @PathVariable("id") int id,@PathVariable("cityid") int cityid){
        SportName name = null;
        City loc=null;
        for(SportName values:SportName.values()) {
            if (values.ordinal() == id) {
                name = values;
                break;
            }
        }
            for(City value:City.values()){
                if(value.ordinal()==cityid)
                {
                    loc=value;
                    break;
                }

        }

        return bookingService.getsportlocationdetails(name,loc);
    }
    @PreAuthorize("hasAuthority('SCOPE_READ')")
    @GetMapping
    public BookingDetailsDTO BookCourt(@RequestBody  UserpreferedenueDetailsDTO detailsDTO) throws ParseException, JsonProcessingException, RazorpayException {
        return bookingService.Bookvenue(detailsDTO);
    }



   // git remote add origin https://github.com/nithish91827/Sportbooking.git

}
