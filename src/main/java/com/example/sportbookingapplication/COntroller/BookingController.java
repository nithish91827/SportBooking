package com.example.sportbookingapplication.COntroller;

import com.example.sportbookingapplication.Bookingservice.BookingService;
import com.example.sportbookingapplication.DTos.UserpreferedenueDetailsDTO;
import com.example.sportbookingapplication.Models.*;
import com.example.sportbookingapplication.Repository.BookngReposiryforsportsvenue;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookingController {

    @Autowired
    private BookingService bookingService;

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

    @PostMapping
    public BookingDetails BookCourt( @RequestBody  UserpreferedenueDetailsDTO detailsDTO) throws ParseException {
        return bookingService.Bookvenue(detailsDTO);
    }
    @GetMapping({"payment/{amount}"})
    public String GenerateLink(@PathVariable("amount") int amount) throws RazorpayException {

      return  bookingService.generatepaymentlink(amount);
    }

}
